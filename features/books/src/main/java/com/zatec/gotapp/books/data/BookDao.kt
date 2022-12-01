package com.zatec.gotapp.books.data

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.zatec.gotapp.books.persistence.BookData
import kotlinx.coroutines.flow.Flow

interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg events: BookData)

    @Transaction
    @Query("SELECT * FROM BookData")
    fun getBooks(): Flow<List<BookData>>

    @Transaction
    @Query("SELECT * FROM BookData")
    fun getPagedBooks(): PagingSource<Int, BookData>

    @Transaction
    @Query("SELECT 1 FROM BookData WHERE id IS :bookId")
    fun getBookById(bookId: String): Flow<BookData>
}