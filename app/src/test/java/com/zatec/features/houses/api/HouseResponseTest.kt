package com.zatec.features.houses.api

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class HouseResponseTest {

    lateinit var houseResponse: HouseResponse

    @Before
    fun setUp() {
        houseResponse = HouseResponse()
    }

    @After
    fun tearDown() {
        TODO("Not yet implemented")
    }

    @Test
    fun givenHouseResponseWhenParamsAreNullThenValuesAreNull() {
        assertEquals(null, houseResponse.ancestralWeapons)
        assertEquals(null, houseResponse.cadetBranches)
        assertEquals(null, houseResponse.name)
        assertEquals(null, houseResponse.coatOfArms)
        assertEquals(null, houseResponse.currentLord)
        assertEquals(null, houseResponse.diedOut)
        assertEquals(null, houseResponse.founded)
        assertEquals(null, houseResponse.founder)
        assertEquals(null, houseResponse.heir)
        assertEquals(null, houseResponse.overlord)
        assertEquals(null, houseResponse.region)
        assertEquals(null, houseResponse.url)
        assertEquals(null, houseResponse.words)
        assertEquals(null, houseResponse.seats)
        assertEquals(null, houseResponse.swornMembers)
        assertEquals(null, houseResponse.titles)
    }

    @Test
    fun givenHouseResponseAsUiWhenParamsAreNullThenDefaultValuesAreUsed() {
        val houseUi = houseResponse.toUi()
        assertEquals(listOf<String>(), houseUi.ancestralWeapons)
        assertEquals(listOf<String>(), houseUi.cadetBranches)
        assertEquals("", houseUi.name)
        assertEquals("", houseUi.coatOfArms)
        assertEquals("", houseUi.currentLord)
        assertEquals("", houseUi.diedOut)
        assertEquals("", houseUi.founded)
        assertEquals("", houseUi.founder)
        assertEquals("", houseUi.heir)
        assertEquals("", houseUi.overlord)
        assertEquals("", houseUi.region)
        assertEquals("", houseUi.url)
        assertEquals("", houseUi.words)
        assertEquals(listOf<String>(), houseUi.seats)
        assertEquals(listOf<String>(), houseUi.swornMembers)
        assertEquals(listOf<String>(), houseUi.titles)
    }


    @Test
    fun givenHouseResponseAsDataWhenParamsAreNullThenDefaultValuesAreUsed() {
        val houseData = houseResponse.toData()
        assertEquals(listOf<String>(), houseData.ancestralWeapons)
        assertEquals(listOf<String>(), houseData.cadetBranches)
        assertEquals("", houseData.name)
        assertEquals("", houseData.coatOfArms)
        assertEquals("", houseData.currentLord)
        assertEquals("", houseData.diedOut)
        assertEquals("", houseData.founded)
        assertEquals("", houseData.founder)
        assertEquals("", houseData.heir)
        assertEquals("", houseData.overlord)
        assertEquals("", houseData.region)
        assertEquals("", houseData.url)
        assertEquals("", houseData.words)
        assertEquals(listOf<String>(), houseData.seats)
        assertEquals(listOf<String>(), houseData.swornMembers)
        assertEquals(listOf<String>(), houseData.titles)
    }
}