package com.zatec.features.characters

import com.zatec.features.characters.data.CharacterRemotePagingMediator
import com.zatec.features.characters.data.CharacterRemotePagingMediatorFactory
import com.zatec.features.characters.repos.CharacterRepository
import com.zatec.features.characters.usecase.PagedCharactersUseCase
import com.zatec.features.characters.usecase.QueryCharactersUseCase
import com.zatec.features.characters.viewmodels.CharactersViewModel
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*

import org.junit.After
import org.junit.Before

class CharactersViewModelTest {

    private lateinit var charactersViewModel: CharactersViewModel
    private val charactersRepo = CharacterRepository(
        FakeCharactersApi(),
        FakeCharactersDao(),
        Dispatchers.IO
    )
    private val characterremotepagingmediatorFactory = FakeCharacterRemotePagingMediatorFactory(charactersRepo)

    @Before
    fun setUp() {
        charactersViewModel = CharactersViewModel(
            pagedCharactersUseCase= PagedCharactersUseCase(
                FakeCharactersDao(),
                characterremotepagingmediatorFactory
            )
        )
    }


    @After
    fun tearDown() {
    }
}