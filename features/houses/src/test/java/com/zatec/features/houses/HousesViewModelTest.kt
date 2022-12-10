package com.zatec.features.houses

import com.zatec.features.houses.repos.HousesRepository
import com.zatec.features.houses.usecase.*
import com.zatec.features.houses.viewmodels.HousesViewModel
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before

class HousesViewModelTest {

    private lateinit var housesViewModel: HousesViewModel
    private val houseRepo = HousesRepository(
        FakeHousesApi(), FakeHouseDao(),Dispatchers.IO
    )
    @Before
    fun setUp() {
        housesViewModel = HousesViewModel(
            pagedHousesUseCase= PagedHousesUseCase(QueryHousesUseCase(houseRepo)),
            housesUseCase= GetHouseUseCase(houseRepo),
            fetchCachedHouseUseCase= FetchCachedHouseUseCase(houseRepo),
            storeHouseUseCase=  StoreHouseUseCase(houseRepo, Dispatchers.IO)
        )
    }

    @After
    fun tearDown() {
    }
}