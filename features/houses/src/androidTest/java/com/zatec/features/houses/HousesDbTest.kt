package com.zatec.features.houses

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import com.zatec.features.houses.api.HouseResponse
import com.zatec.features.houses.data.HouseDao
import com.zatec.features.houses.data.HouseDatabase
import com.zatec.gotapp.core.data.StringListConverter
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class HousesDbTest {
    private lateinit var houseDao: HouseDao
    private lateinit var db: HouseDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, HouseDatabase::class.java)
            .addTypeConverter(StringListConverter(Moshi.Builder().build()))
            .build()
        houseDao = db.houseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        //db.close()
    }

    @Test
    @Throws(Exception::class)
    fun givenDbWhenHouseIsWrittenThenHouseCanBeRead() {
        val houseResponse = HouseResponse(
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
        val houseData = houseResponse.toData()
        houseDao.insert(houseData)
        val readHouse = houseDao.getHouseById("1")

        runBlocking {
            val job = async {
                readHouse.take(1).toList()
            }

            val house = job.await()

            assertEquals(house[0]?.id, houseData.id)
        }
    }
}