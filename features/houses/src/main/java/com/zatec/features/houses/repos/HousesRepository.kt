package com.zatec.features.houses.repos

import com.zatec.features.houses.api.HousesApi
import com.zatec.features.houses.api.HouseResponse
import com.zatec.gotapp.core.api.ApiResponse
import javax.inject.Inject

/**
 * Houses repository
 * instance of [HousesRepo] and implements all its functions
 * @property housesApi
 * @constructor Create empty Houses repository
 */
class HousesRepository @Inject constructor(
    private val housesApi: HousesApi
): HousesRepo{

    //api call to return houses in a page as a list
    override suspend fun queryHouses(page: Int?, size: Int): ApiResponse<List<HouseResponse>> =
        housesApi.queryHouses(page = page, pageSize = size)

    //api call to return a particular house
    override suspend fun getHouseById(houseId: Int): ApiResponse<HouseResponse> =
        housesApi.getHouseById(houseId)


    override fun getHouseFromCache(houseId: Int) {
        TODO("Not yet implemented")
    }

}