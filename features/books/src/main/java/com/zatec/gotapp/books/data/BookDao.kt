package com.zatec.gotapp.books.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.zatec.gotapp.books.persistence.BookData
import kotlinx.coroutines.flow.Flow


/**
 * Book dao
 * Data access object for [BookData] stored in room db
 * currently unused
 * @constructor Create empty Book dao
 */
@Dao
interface BookDao {
    /**
     * Insert
     * add a single book object / list of book objects to room db
     * @param events
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg events: BookData)

    /**
     * Get books
     * returns a list of all [BookData] objects from room
     * @return
     */
    @Transaction
    @Query("SELECT * FROM BookData")
    fun getBooks(): Flow<List<BookData>>

    /**
     * Get paged books
     * returns a page of books from db
     * @return
     */
    @Transaction
    @Query("SELECT * FROM BookData")
    fun getPagedBooks(): PagingSource<Int, BookData>

    /**
     * Get book by id
     * returns a specific book based on its id
     * @param bookId the id of the book
     * @return
     */
    @Transaction
    @Query("SELECT * FROM BookData WHERE id IS :bookId LIMIT 1")
    fun getBookById(bookId: String): Flow<BookData>
}