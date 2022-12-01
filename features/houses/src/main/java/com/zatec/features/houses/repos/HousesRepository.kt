package com.zatec.features.houses.repos

import com.zatec.features.houses.api.HousesApi
import com.zatec.features.houses.data.HouseResponse
import com.zatec.gotapp.core.api.ApiResponse
import javax.inject.Inject

class HousesRepository @Inject constructor(
    private val housesApi: HousesApi
): HousesRepo{
    override suspend fun queryHouses(page: Int?, size: Int): ApiResponse<List<HouseResponse>> =
        housesApi.queryHouses(page = page, pageSize = size)


    override suspend fun getHouseById(houseId: Int): ApiResponse<HouseResponse> =
        housesApi.getHouseById(houseId)


    override fun getHouseFromCache(houseId: Int) {
        TODO("Not yet implemented")
    }

}