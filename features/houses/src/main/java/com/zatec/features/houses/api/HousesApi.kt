package com.zatec.features.houses.api

import com.zatec.features.houses.api.Routes.api
import com.zatec.features.houses.api.Routes.houseId
import com.zatec.features.houses.api.Routes.houses
import com.zatec.features.houses.data.HouseResponse
import com.zatec.gotapp.core.api.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HousesApi {

    @GET("$api$houses")
    suspend fun queryHouses(
        @Query(QueryParams.page) page: Int?,
        @Query(QueryParams.pageSize) pageSize: Int?
    ): ApiResponse<List<HouseResponse>>

    @GET("$api$houses$houseId")
    suspend fun getHouseById(
        @Path(Routes.houseId) houseId: Int?
    ): ApiResponse<HouseResponse>

}

object Routes {
    const val api = "/api"
    const val houses = "/houses"
    const val houseId = "/{houseId}"
}

object QueryParams{
    const val page = "page"
    const val pageSize = "pageSize"
}