package com.zatec.features.houses.repos

import com.zatec.features.houses.api.HouseResponse
import com.zatec.features.houses.persistence.HouseData
import com.zatec.gotapp.core.api.ApiResponse
import kotlinx.coroutines.flow.Flow


/**
 * Houses repo
 * interface for interacting with houses data points
 * @constructor Create empty Houses repo
 */
interface HousesRepo {
    /**
     * Query houses
     *
     * @param page page number of request
     * @param size number of items per page
     * @return
     */
    suspend fun queryHouses(page: Int?, size: Int): ApiResponse<List<HouseResponse>>

    /**
     * Get house by id
     *
     * @param houseId
     * @return [HouseResponse] wrapped as an [ApiResponse]
     */
    suspend fun getHouseById(houseId: String): ApiResponse<HouseResponse>

    /**
     * Get house from cache
     * return housedata object from cache
     * @param houseId
     */
    fun getHouseFromCache(houseId: String): Flow<HouseData?>

    /**
     * save house into room db
     * @param houseData
     */
    suspend fun storeHouseInDb(houseData: HouseData)
}
