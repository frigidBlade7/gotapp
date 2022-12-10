package com.zatec.features.characters

import com.zatec.features.characters.data.CharacterRemotePagingMediator
import com.zatec.features.characters.data.CharacterRemotePagingMediatorFactory
import com.zatec.features.characters.repos.CharactersRepo
import com.zatec.features.characters.usecase.QueryCharactersUseCase

class FakeCharacterRemotePagingMediatorFactory(
    private val charactersRepo: CharactersRepo
): CharacterRemotePagingMediatorFactory {

    override fun create(page: Int?, size: Int): CharacterRemotePagingMediator {
        return CharacterRemotePagingMediator(
            queryCharactersUseCase = QueryCharactersUseCase(charactersRepo),
            page,
            size,
            charactersRepo
        )
    }
}