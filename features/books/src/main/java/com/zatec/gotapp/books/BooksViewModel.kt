package com.zatec.gotapp.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zatec.gotapp.books.ui.BookUi
import com.zatec.gotapp.books.usecase.PagedBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksUseCase: PagedBooksUseCase
): ViewModel() {

    init {
        getBooks()
    }

    private val _pagedBooks = MutableSharedFlow<PagingData<BookUi>>()
    val pagedBooks: Flow<PagingData<BookUi>>
        get() = _pagedBooks.asSharedFlow()

    private fun getBooks(page: Int = 1, size: Int = 10){
        viewModelScope.launch {
            booksUseCase.invoke(page= page, size = size)
                .cachedIn(viewModelScope).collectLatest {
                    Timber.d(it.toString())
                    _pagedBooks.emit(it)
                }
        }
    }
}