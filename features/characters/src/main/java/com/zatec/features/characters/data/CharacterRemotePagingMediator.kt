package com.zatec.features.characters.data

import androidx.paging.*
import androidx.room.withTransaction
import com.zatec.features.characters.persistence.CharacterData
import com.zatec.features.characters.usecase.QueryCharactersUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber


/**
 * Character remote paging mediator
 *
 * @property queryCharactersUseCase api usecase to request character data
 * @property page page number of items to be loaded
 * @property size number of items per page
 * @property database containing table for [CharacterData] objects
 * @constructor Create empty Character remote paging mediator
 */
@OptIn(ExperimentalPagingApi::class)
class CharacterRemotePagingMediator @AssistedInject constructor(
    private val queryCharactersUseCase: QueryCharactersUseCase,
    @Assisted private var page: Int?,
    @Assisted private val size: Int,
    private val database: CharacterDatabase
    //todo can inject CharacterRepo here to interface with both usecase and data
): RemoteMediator<Int, CharacterData>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterData>
    ): MediatorResult {

        page = when(loadType){
            LoadType.REFRESH ->{
                1
            }
            LoadType.PREPEND -> {
                val prev = page?.minus(1)
                return MediatorResult.Success(prev == null || prev < 1)
            }
            LoadType.APPEND -> {
                page?.plus(1)
                //return MediatorResult.Success(false)
            }
        }

        try{
            lateinit var loadResult: MediatorResult
            queryCharactersUseCase.invoke(page?:1,size).collectLatest {
                it.errorMessage?.apply {
                    throw (Exception(it.message))
                }

                it.errorCode?.apply {
                    throw (Exception(this.toString()))
                }

//                if(page == 4){
//                    throw (Exception("nate"))
//                }

                it.data?.let { data ->
                    database.withTransaction {
                        if(loadType == LoadType.REFRESH)
                            database.characterDao().clear()

                        database.characterDao().insert(*data.map { character-> character.toData() }.toTypedArray())
                    }

                    val endReached = it.lastPage == null || page!! >= it.lastPage!!

                    loadResult = MediatorResult.Success(endReached)
                }
            }

            return loadResult
        }catch (e: Exception){
            Timber.e(e)
            return MediatorResult.Error(e)
        }
    }
}


/**
 * Character remote paging mediator factory
 * factory to help assisted injection of dynamic page number and size
 * @constructor Create empty Character remote paging mediator factory
 */
@AssistedFactory
interface CharacterRemotePagingMediatorFactory {
    /**
     * Create
     *
     * @param page page number of items to be loaded
     * @param size number of items per page
     * @return [CharacterRemotePagingMediator] object with usecase and database injected
     */
    fun create(page: Int? = null, size: Int = 50): CharacterRemotePagingMediator
}

