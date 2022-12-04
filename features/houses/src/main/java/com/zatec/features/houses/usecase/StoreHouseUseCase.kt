package com.zatec.features.houses.usecase

import com.zatec.features.houses.api.HouseResponse
import com.zatec.features.houses.repos.HousesRepo
import com.zatec.gotapp.core.utils.IOContext
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 *  use case to store a single house in room db
 *
 * @property housesRepo
 * @constructor Create empty query single house use case
 */
class StoreHouseUseCase @Inject constructor(
    private val housesRepo: HousesRepo,
    @IOContext val coroutineContext: CoroutineContext
) {
    /**
     * Invoke
     * store [HouseResponse] in db as [HouseData]
     */
    suspend operator fun invoke(houseResponse: HouseResponse) = withContext(coroutineContext){
        housesRepo.storeHouseInDb(houseResponse.toData())
    }

}