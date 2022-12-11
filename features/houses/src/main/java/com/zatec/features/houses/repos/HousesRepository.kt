package com.zatec.features.houses.repos

import androidx.room.withTransaction
import com.zatec.features.houses.api.HousesApi
import com.zatec.features.houses.api.HouseResponse
import com.zatec.features.houses.data.HouseDao
import com.zatec.features.houses.data.HouseDatabase
import com.zatec.features.houses.persistence.HouseData
import com.zatec.gotapp.core.api.ApiResponse
import com.zatec.gotapp.core.utils.IOContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Houses repository
 * instance of [HousesRepo] and implements all its functions
 * @property housesApi
 * @constructor Create empty Houses repository
 */
class HousesRepository @Inject constructor(
    private val housesApi: HousesApi,
    private val houseDao: HouseDao,
    @IOContext val coroutineContext: CoroutineContext
): HousesRepo{

    //api call to return houses in a page as a list
    override suspend fun queryHouses(page: Int?, size: Int): ApiResponse<List<HouseResponse>> =
        housesApi.queryHouses(page = page, pageSize = size)

    //api call to return a particular house
    override suspend fun getHouseById(houseId: String): ApiResponse<HouseResponse> =
        housesApi.getHouseById(houseId)


    override fun getHouseFromCache(houseId: String): Flow<HouseData?> =
        houseDao.getHouseById(houseId)

    override suspend fun storeHouseInDb(houseData: HouseData) {
        withContext(coroutineContext){
            houseDao.insert(houseData)
        }
    }

}