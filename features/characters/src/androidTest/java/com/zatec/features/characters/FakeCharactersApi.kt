package com.zatec.features.characters

import android.net.Uri
import com.zatec.features.characters.api.CharactersApi
import com.zatec.features.characters.data.CharacterResponse
import com.zatec.gotapp.core.api.ApiResponse

class FakeCharactersApi (
    private val characters: List<CharacterResponse>? = listOf()
): CharactersApi {
    override suspend fun queryCharacters(
        page: Int?,
        pageSize: Int?
    ): ApiResponse<List<CharacterResponse>> = ApiResponse.Success(data = characters, page)

    override suspend fun getCharacterById(characterId: Int?): ApiResponse<CharacterResponse> =
        ApiResponse.Success(characters?.find { Uri.parse(it.url).lastPathSegment?.toInt() == characterId })
}