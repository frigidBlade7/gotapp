package com.zatec.features.houses

import com.zatec.features.houses.api.HouseResponse
import com.zatec.features.houses.persistence.HouseData
import com.zatec.features.houses.repos.HousesRepository
import com.zatec.features.houses.usecase.*
import com.zatec.features.houses.viewmodels.HousesViewModel
import com.zatec.gotapp.core.api.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HousesViewModelTest {

    private lateinit var housesViewModel: HousesViewModel
    private val housesResponse = HouseResponse(
        url = "https://anapioficeandfire.com/api/houses/1",
        name = "House Algood",
        region = "The Westerlands",
        coatOfArms = "A golden wreath, on a blue field with a gold border(Azure, a garland of laurel within a bordure or)",
    )


    private var houseDao = FakeHouseDao(mutableListOf(housesResponse.toData()))
    private val houseApi = FakeHousesApi(listOf(housesResponse))
    private val houseRepo = HousesRepository(
        houseApi, houseDao,Dispatchers.IO
    )

    @Test
    fun givenViewmodelwhenhappypathHouseApidatashouldbesame(){
        runBlocking {
            houseApi.queryHouses(1,10).apply {
                this as ApiResponse.Success
                assertEquals(1,this.data?.size)
                assertEquals("House Algood",this.data?.first()?.name)
            }
        }
    }

    @Test
    fun givenViewmodelwhenhappypathHouseDatadatashouldbesame(){
        runBlocking {
            houseDao.getHouses().collectLatest {
                assertEquals(1,it.size)
                assertEquals("House Algood",it.first().name)
            }
        }
    }

    /*@Test
    fun givenViewmodelwhengethousecalledThenHouseapicalledandhousedatasaved(){
        houseDao = FakeHouseDao()
        runBlocking {
            housesViewModel.getHouse("1")
            houseDao.getHouses()
            assertEquals(1, ))

        }
    }*/


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