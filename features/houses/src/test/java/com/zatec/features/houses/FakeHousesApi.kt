package com.zatec.features.houses

import com.zatec.features.houses.api.HouseResponse
import com.zatec.features.houses.api.HousesApi
import com.zatec.gotapp.core.api.ApiResponse

class FakeHousesApi (private val houseList: List<HouseResponse>? = listOf()): HousesApi {
    override suspend fun queryHouses(page: Int?, pageSize: Int?): ApiResponse<List<HouseResponse>>
    = ApiResponse.Success(data = houseList, page)

    override suspend fun getHouseById(houseId: String): ApiResponse<HouseResponse> =
        ApiResponse.Success(houseList?.first())
}