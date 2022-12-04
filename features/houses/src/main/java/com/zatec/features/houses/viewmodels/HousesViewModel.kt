package com.zatec.features.houses.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zatec.features.houses.persistence.toUi
import com.zatec.features.houses.ui.HouseUi
import com.zatec.features.houses.usecase.FetchCachedHouseUseCase
import com.zatec.features.houses.usecase.GetHouseUseCase
import com.zatec.features.houses.usecase.PagedHousesUseCase
import com.zatec.features.houses.usecase.StoreHouseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Books view model
 * viewmodel for ListFragment
 * @property housesUseCase use case for paging books from api
 * @constructor Create empty Books view model
 */
@HiltViewModel
class HousesViewModel @Inject constructor(
    private val pagedHousesUseCase: PagedHousesUseCase,
    private val housesUseCase: GetHouseUseCase,
    private val fetchCachedHouseUseCase : FetchCachedHouseUseCase,
    private val storeHouseUseCase: StoreHouseUseCase
): ViewModel() {

    private val _pagedHouses = MutableSharedFlow<PagingData<HouseUi>>()
    val pagedHouses: Flow<PagingData<HouseUi>>
        get() = _pagedHouses.asSharedFlow()

    fun getHouses(page: Int = 1, size: Int = 25){
        viewModelScope.launch {
            pagedHousesUseCase.invoke(page= page, size = size)
                .cachedIn(viewModelScope).collectLatest {
                    Timber.d(it.toString())
                    _pagedHouses.emit(it)
                }
        }
    }

    fun flowHouse(houseId: String) = fetchCachedHouseUseCase.invoke(houseId).map { it?.toUi() }

    fun getHouse(houseId: String){
        viewModelScope.launch {
            housesUseCase.invoke(houseId).collectLatest {
                it.errorMessage?.apply {
                    Timber.e(this)
                }

                it.errorCode?.apply {
                    Timber.e(this.toString())
                }

                it.data?.apply {
                    storeHouseUseCase.invoke(this)
                }
            }
        }
    }
}