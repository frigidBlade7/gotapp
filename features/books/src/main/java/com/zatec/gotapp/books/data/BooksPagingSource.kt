package com.zatec.gotapp.books.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zatec.gotapp.books.ui.BookUi
import com.zatec.gotapp.books.usecase.QueryBooksUseCase
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import java.io.IOException

class BooksPagingSource (
    private val queryBooksUseCase: QueryBooksUseCase,
    private val page: Int,
    private val size: Int
    ): PagingSource<Int, BookUi>(){

    override fun getRefreshKey(state: PagingState<Int, BookUi>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookUi> {
        val currentPage = params.key?: page

        return try {
            var pagedData: List<BookResponse>
            var nextKey: Int?
            val response =  queryBooksUseCase.invoke(size= size, page= currentPage)

            lateinit var result: LoadResult<Int,BookUi>

            response.collectLatest {

                it.errorMessage?.apply {
                    result = LoadResult.Error(Exception(it.message))
                }

                it.errorCode?.apply {
                    result = LoadResult.Error(Exception(this.toString()))
                }

                it.data?.apply {
                    pagedData = this
                    nextKey = if(it.lastPage == null || currentPage >= it.lastPage!!) null
                    else currentPage + 1

                    result = LoadResult.Page(
                        data = pagedData.map { response -> response.toUi() },
                        prevKey = if (currentPage==1) null else currentPage -1,
                        nextKey= nextKey)
                }
            }

            result
        }

        catch (exception: IOException){
            Timber.e(exception)
            return LoadResult.Error(exception)
        }
        catch (exception: Exception){
            Timber.e(exception)
            return LoadResult.Error(exception)
        }
    }

}