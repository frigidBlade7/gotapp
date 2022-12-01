package com.zatec.features.houses.repos

import com.zatec.features.houses.data.HouseResponse
import com.zatec.gotapp.core.api.ApiResponse

interface HousesRepo {
    suspend fun queryHouses(page: Int?, size: Int): ApiResponse<List<HouseResponse>>
    suspend fun getHouseById(houseId: Int): ApiResponse<HouseResponse>

    fun getHouseFromCache(houseId: Int)
}
