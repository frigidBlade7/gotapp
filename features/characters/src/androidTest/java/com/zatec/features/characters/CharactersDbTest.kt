package com.zatec.features.characters

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import com.zatec.features.characters.data.CharacterDao
import com.zatec.features.characters.data.CharacterDatabase
import com.zatec.features.characters.data.CharacterResponse
import com.zatec.gotapp.core.data.StringListConverter
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CharactersDbTest {
    private lateinit var characterDao: CharacterDao
    private lateinit var db: CharacterDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CharacterDatabase::class.java)
            .addTypeConverter(StringListConverter(Moshi.Builder().build()))
            .build()
        characterDao = db.characterDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        //db.close()
    }

    @Test
    @Throws(Exception::class)
    fun givenDbWhenCharacterIsWrittenThenCharacterCanBeRead() {
        val characterResponse = CharacterResponse(
            name = "Nate",
            titles = listOf("android dev","the choosen one"),
            aliases = listOf("android dev","the choosen one"),
            books = listOf("https://anapioficeandfire.com/api/books/5"),
            born = "299 AC, at Pyke",
            culture = "TDD",
            father = "Attipoe X",
            mother = "Attipoe Y",
            allegiances = listOf("https://anapioficeandfire.com/api/houses/195"),
            url = "https://anapioficeandfire.com/api/characters/1"
        )
        val characterData = characterResponse.toData()
        characterDao.insert(characterData)
        val readCharacter = characterDao.getCharacterById("1")
        runBlocking {
            val job = async {
                readCharacter.take(1).toList()
            }

            val character = job.await()

            assertEquals(character[0]?.id, characterData.id)
        }
    }
}