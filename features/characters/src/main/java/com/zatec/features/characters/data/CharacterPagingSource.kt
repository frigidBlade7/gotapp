package com.zatec.features.characters.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zatec.features.characters.ui.CharacterUi
import com.zatec.features.characters.usecase.QueryCharactersUseCase
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import java.io.IOException

class CharacterPagingSource(
    private val queryCharactersUseCase: QueryCharactersUseCase,
    private val page: Int,
    private val size: Int
    ): PagingSource<Int, CharacterUi>(){

    override fun getRefreshKey(state: PagingState<Int, CharacterUi>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterUi> {
        val currentPage = params.key?: page

        return try {
            var pagedData: List<CharacterResponse>
            var nextKey: Int?
            val response =  queryCharactersUseCase.invoke(size= size, page= currentPage)

            lateinit var result: LoadResult<Int,CharacterUi>

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