package com.zatec.features.houses.api

import com.zatec.features.houses.api.Routes.api
import com.zatec.features.houses.api.Routes.houseId
import com.zatec.features.houses.api.Routes.houses
import com.zatec.gotapp.core.api.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Houses api
 *
 * @constructor Create empty Houses api
 */
interface HousesApi {

    /**
     * Query houses
     *
     * @param page the page number for the request
     * @param pageSize max number of items in a page
     * @return [ApiResponse] of house item
     */
    @GET("$api$houses")
    suspend fun queryHouses(
        @Query(QueryParams.page) page: Int?,
        @Query(QueryParams.pageSize) pageSize: Int?
    ): ApiResponse<List<HouseResponse>>

    /**
     * Get house by id
     *
     * @param houseId
     * @return a list of houses wrapped by [ApiResponse]
     */
    @GET("$api$houses/{$houseId}")
    suspend fun getHouseById(
        @Path(Routes.houseId) houseId: String
    ): ApiResponse<HouseResponse>

}

object Routes {
    const val api = "/api"
    const val houses = "/houses"
    const val houseId = "houseId"
}

object QueryParams{
    const val page = "page"
    const val pageSize = "pageSize"
}