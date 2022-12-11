package com.zatec.features.houses.usecase

import com.zatec.features.houses.data.HouseDatabase
import com.zatec.features.houses.repos.HousesRepo
import com.zatec.gotapp.core.utils.flowResult
import javax.inject.Inject

/**
 * Query houses use case
 *
 * @property housesRepo
 * @constructor Create empty query single house use case
 */
class GetHouseUseCase @Inject constructor(
    private val housesRepo: HousesRepo
) {
    /**
     * Invoke
     *
     * @param houseId id of house
     */
    operator fun invoke(houseId: String) = flowResult {
        housesRepo.getHouseById(houseId)
    }
}