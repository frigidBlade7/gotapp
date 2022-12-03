package com.zatec.features.houses.usecase

import com.zatec.features.houses.repos.HousesRepo
import com.zatec.gotapp.core.utils.flowResult
import javax.inject.Inject

/**
 * Query houses use case
 *
 * @property housesRepo
 * @constructor Create empty Query houses use case
 */
class QueryHousesUseCase @Inject constructor(
    private val housesRepo: HousesRepo
) {
    /**
     * Invoke
     *
     * @param size number of items per page
     * @param page page number of load
     */
    operator fun invoke(page: Int = 1, size: Int = 50) = flowResult {
        housesRepo.queryHouses(page, size)
    }
}