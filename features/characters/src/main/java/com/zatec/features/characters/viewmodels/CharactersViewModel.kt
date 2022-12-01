package com.zatec.features.characters.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zatec.features.characters.ui.CharacterUi
import com.zatec.features.characters.usecase.PagedCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val pagedCharactersUseCase: PagedCharactersUseCase
): ViewModel() {

    init {
        getCharacters()
    }

    private val _pagedCharacters = MutableSharedFlow<PagingData<CharacterUi>>()
    val pagedCharacters: Flow<PagingData<CharacterUi>>
        get() = _pagedCharacters.asSharedFlow()

    private fun getCharacters(page: Int = 1, size: Int = 25){
        viewModelScope.launch {
            pagedCharactersUseCase.invoke(page= page, size = size)
                .cachedIn(viewModelScope).collectLatest {
                    Timber.d(it.toString())
                    _pagedCharacters.emit(it)
                }
        }
    }
}