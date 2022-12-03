package com.zatec.gotapp.core.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

/**
 * String list converter
 * Helps us to convert a list of parameterized objects to a string and back
 * so we can store these wholly in the room db
 * prefer this over @Embedded since most nested lists are of <String> type
 * @property moshi injected moshi instance to help parse json list into string and back
 * @constructor Create empty String list converter
 */
@ProvidedTypeConverter
class StringListConverter @Inject constructor(val moshi: Moshi)
    : ListConverter<String> {
    private val dataListType =
        Types.newParameterizedType(MutableList::class.java, String::class.java)
    private val dataListAdapter: JsonAdapter<List<String>> = moshi.adapter(dataListType)

    @TypeConverter
    override fun listToDataJson(list: List<String>): String =
        dataListAdapter.toJson(list)

    @TypeConverter
    override fun dataJsonToList(data: String): List<String> =
        dataListAdapter.fromJson(data)?: listOf()
}

/**
 * List converter
 *
 * @param T
 * @constructor Create empty List converter
 */
interface ListConverter<T>{

    /**
     * List to data json
     *
     * @param list
     * @return
     */
    @TypeConverter
    fun listToDataJson(list: List<T>): String

    /**
     * Data json to list
     *
     * @param data
     * @return
     */
    @TypeConverter
    fun dataJsonToList(data: String): List<T>
}