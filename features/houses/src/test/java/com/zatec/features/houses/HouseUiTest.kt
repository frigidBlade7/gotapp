package com.zatec.features.houses

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zatec.features.houses.api.HouseResponse
import com.zatec.features.houses.ui.*
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HouseUiTest {
    lateinit var houseResponse: HouseResponse
    lateinit var partialHouseResponse: HouseResponse
    lateinit var houseTwoSeats: HouseResponse

    @Before
    fun setUp() {
        houseResponse = HouseResponse(
            ancestralWeapons = listOf("pickaxe"),
            cadetBranches = listOf("alpha"),
            name = "House Attipoe",
            coatOfArms = "A developer behind his macbook, writing tests",
            currentLord = "Nathany Attipoe",
            region = "Accra, Ghana",
            url = "https://anapioficeandfire.com/api/houses/1",
            words = "Tabs over spaces!",
            seats = listOf("TabTower"),
            swornMembers = listOf("noone"),
            titles = listOf("Android Dev @ Zatec")
        )

        partialHouseResponse = HouseResponse(
            name = "House LittleInfo"
        )
        houseTwoSeats = HouseResponse(
            name = "House Musical Chairs Deadlock",
            seats = listOf("Seat 1", "Seat 2"),
            ancestralWeapons = listOf("fists", "feet")
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun givenHouseResponseAsUiWhenParamsAreNotNullThenUiValuesAreUsed() {
        val houseUi = houseResponse.toUi()
        assertEquals("pickaxe", houseUi.ancestralWeapons.first())
        assertEquals(listOf<String>("alpha"), houseUi.cadetBranches)
        assertEquals("House Attipoe", houseUi.name)
        assertEquals("A developer behind his macbook, writing tests", houseUi.coatOfArms)
        assertEquals("Nathany Attipoe", houseUi.currentLord)
        assertEquals("", houseUi.diedOut)
        assertEquals("", houseUi.founded)
        assertEquals("", houseUi.founder)
        assertEquals("", houseUi.heir)
        assertEquals("", houseUi.overlord)
        assertEquals("Accra, Ghana", houseUi.region)
        assertEquals("https://anapioficeandfire.com/api/houses/1", houseUi.url)
        assertEquals("Tabs over spaces!", houseUi.words)
        assertEquals(listOf("TabTower"), houseUi.seats)
        assertEquals("noone", houseUi.swornMembers.first())
        assertEquals("Android Dev @ Zatec", houseUi.titles.first())
    }

    @Test
    fun givenHouseResponseAsDataWhenParamsAreNotNullThenDataValuesAreUsed() {
        val houseData = houseResponse.toData()
        assertEquals("pickaxe", houseData.ancestralWeapons.first())
        assertEquals(listOf<String>("alpha"), houseData.cadetBranches)
        assertEquals("House Attipoe", houseData.name)
        assertEquals("A developer behind his macbook, writing tests", houseData.coatOfArms)
        assertEquals("Nathany Attipoe", houseData.currentLord)
        assertEquals("", houseData.diedOut)
        assertEquals("", houseData.founded)
        assertEquals("", houseData.founder)
        assertEquals("", houseData.heir)
        assertEquals("", houseData.overlord)
        assertEquals("Accra, Ghana", houseData.region)
        assertEquals("https://anapioficeandfire.com/api/houses/1", houseData.url)
        assertEquals("Tabs over spaces!", houseData.words)
        assertEquals(listOf("TabTower"), houseData.seats)
        assertEquals("noone", houseData.swornMembers.first())
        assertEquals("Android Dev @ Zatec", houseData.titles.first())
        assertEquals("1", houseData.id)
    }

    @Test
    fun givenHouseWhenHasNoSeatsdisplayEmptyString(){
        assertEquals("", partialHouseResponse.toUi().displaySeats())
    }

    @Test
    fun givenHouseWhenHas1SeatdisplaySeatString(){
        assertEquals("TabTower", houseResponse.toUi().displaySeats())
    }

    @Test
    fun givenHouseWhenHasMultipleSeatsdisplaySeatString(){
        assertEquals("Seat 1, Seat 2", houseTwoSeats.toUi().displaySeats())
    }

    @Test
    fun givenHouseWhenHasNoWeaponsdisplayEmptyString(){
        assertEquals("", partialHouseResponse.toUi().displayWeapons())
    }

    @Test
    fun givenHouseWhenHas1SeatdisplayWeaponsString(){
        assertEquals("pickaxe", houseResponse.toUi().displayWeapons())
    }

    @Test
    fun givenHouseWhenHasMultipleWeaponsdisplayWeaponsString(){
        assertEquals("fists, feet", houseTwoSeats.toUi().displayWeapons())
    }


    @Test
    fun givenHouseWhenHasLordThendisplayLord() {
        assertEquals("Nathany Attipoe",houseResponse.toUi().displayLord())
    }

    @Test
    fun givenHouseWhenHasNoLordThendisplayDefault() {
        assertEquals("A house has no Lord",partialHouseResponse.toUi().displayLord())
    }

    @Test
    fun displayLocation() {
        assertEquals("No known location",partialHouseResponse.toUi().displayLocation())
        assertEquals("Accra, Ghana",houseResponse.toUi().displayLocation())
    }

    @Test
    fun seatCount() {
        assertEquals(0, partialHouseResponse.toUi().seatCount())
        assertEquals(1, houseResponse.toUi().seatCount())
        assertEquals(2, houseTwoSeats.toUi().seatCount())
    }
}