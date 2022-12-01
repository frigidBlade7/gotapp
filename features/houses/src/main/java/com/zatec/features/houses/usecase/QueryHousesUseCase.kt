package com.zatec.features.houses.usecase

import com.zatec.features.houses.repos.HousesRepo
import com.zatec.gotapp.core.utils.flowResult
import javax.inject.Inject

class QueryHousesUseCase @Inject constructor(
    private val housesRepo: HousesRepo
) {
    operator fun invoke(page: Int = 1, size: Int = 50) = flowResult {
        housesRepo.queryHouses(page, size)
    }
}