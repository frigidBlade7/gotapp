package com.zatec.gotapp.books.data

import com.zatec.gotapp.books.persistence.BookData
import com.zatec.gotapp.books.ui.BookUi
import com.zatec.gotapp.core.data.DataMapper

class BookMapper: DataMapper<BookResponse, BookData, BookUi> {

    override fun toData(): BookData {
        TODO("Not yet implemented")
    }

    override fun toUi(): BookUi {
        TODO("Not yet implemented")
    }
}