package com.zatec.features.houses.usecase

import com.zatec.features.houses.repos.HousesRepo
import javax.inject.Inject

/**
 *  use case to fetch a single house from room db
 *
 * @property housesRepo
 * @constructor Create empty query single house use case
 */
class FetchCachedHouseUseCase @Inject constructor(
    private val housesRepo: HousesRepo
) {
    /**
     * Invoke
     *
     * @param houseId id of house
     */
    operator fun invoke(houseId: String) = housesRepo.getHouseFromCache(houseId)
}