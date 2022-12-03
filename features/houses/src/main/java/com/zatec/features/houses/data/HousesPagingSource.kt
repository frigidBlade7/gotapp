package com.zatec.features.houses.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zatec.features.houses.api.HouseResponse
import com.zatec.features.houses.ui.HouseUi
import com.zatec.features.houses.usecase.QueryHousesUseCase
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import java.io.IOException


/**
 * Houses paging source
 *
 * @property queryHousesUseCase use case implementation for api queries
 * @property page page number of query
 * @property size items per page
 * @constructor Create empty Houses paging source
 */
class HousesPagingSource(
    private val queryHousesUseCase: QueryHousesUseCase,
    private val page: Int,
    private val size: Int
    ): PagingSource<Int, HouseUi>(){

    override fun getRefreshKey(state: PagingState<Int, HouseUi>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HouseUi> {
        val currentPage = params.key?: page

        return try {
            var pagedData: List<HouseResponse>
            var nextKey: Int?
            val response =  queryHousesUseCase.invoke(size= size, page= currentPage)

            lateinit var result: LoadResult<Int,HouseUi>

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