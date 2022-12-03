package com.zatec.features.characters.api

import com.zatec.features.characters.api.Routes.api
import com.zatec.features.characters.api.Routes.CharacterId
import com.zatec.features.characters.api.Routes.Characters
import com.zatec.features.characters.data.CharacterResponse
import com.zatec.gotapp.core.api.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Characters api
 * rest client interface for api calls
 * @constructor Create empty Characters api
 */
interface CharactersApi {

    /**
     * Query characters
     *
     * @param page page number of the request
     * @param pageSize number of items per page
     * @return
     */
    @GET("$api$Characters")
    suspend fun queryCharacters(
        @Query(QueryParams.page) page: Int?,
        @Query(QueryParams.pageSize) pageSize: Int?
    ): ApiResponse<List<CharacterResponse>>

    /**
     * Get character by id
     * api request to fetch specific character data by id
     * @param characterId
     * @return
     */
    @GET("$api$Characters$CharacterId")
    suspend fun getCharacterById(
        @Path(CharacterId) characterId: Int?
    ): ApiResponse<CharacterResponse>

}

object Routes {
    const val api = "/api"
    const val Characters = "/characters"
    const val CharacterId = "/{characterId}"
}

object QueryParams{
    const val page = "page"
    const val pageSize = "pageSize"
}