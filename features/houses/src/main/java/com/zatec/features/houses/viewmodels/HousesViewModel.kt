package com.zatec.features.houses.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zatec.features.houses.ui.HouseUi
import com.zatec.features.houses.usecase.PagedHousesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HousesViewModel @Inject constructor(
    private val housesUseCase: PagedHousesUseCase
): ViewModel() {

    init {
        getHouses()
    }

    private val _pagedHouses = MutableSharedFlow<PagingData<HouseUi>>()
    val pagedHouses: Flow<PagingData<HouseUi>>
        get() = _pagedHouses.asSharedFlow()

    private fun getHouses(page: Int = 1, size: Int = 25){
        viewModelScope.launch {
            housesUseCase.invoke(page= page, size = size)
                .cachedIn(viewModelScope).collectLatest {
                    Timber.d(it.toString())
                    _pagedHouses.emit(it)
                }
        }
    }
}