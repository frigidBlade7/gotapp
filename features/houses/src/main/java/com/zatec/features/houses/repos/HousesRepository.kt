package com.zatec.features.houses.repos

import androidx.room.withTransaction
import com.zatec.features.houses.api.HousesApi
import com.zatec.features.houses.api.HouseResponse
import com.zatec.features.houses.data.HouseDatabase
import com.zatec.features.houses.persistence.HouseData
import com.zatec.gotapp.core.api.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Houses repository
 * instance of [HousesRepo] and implements all its functions
 * @property housesApi
 * @constructor Create empty Houses repository
 */
class HousesRepository @Inject constructor(
    private val housesApi: HousesApi,
    private val houseDatabase: HouseDatabase
): HousesRepo{

    //api call to return houses in a page as a list
    override suspend fun queryHouses(page: Int?, size: Int): ApiResponse<List<HouseResponse>> =
        housesApi.queryHouses(page = page, pageSize = size)

    //api call to return a particular house
    override suspend fun getHouseById(houseId: String): ApiResponse<HouseResponse> =
        housesApi.getHouseById(houseId)


    override fun getHouseFromCache(houseId: String): Flow<HouseData?> =
        houseDatabase.houseDao().getHouseById(houseId)

    override suspend fun storeHouseInDb(houseData: HouseData) {
        houseDatabase.withTransaction {
            houseDatabase.houseDao().insert(houseData)
        }
    }


}