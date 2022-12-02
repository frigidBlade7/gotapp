package com.zatec.gotapp.core.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

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

interface ListConverter<T>{

    @TypeConverter
    fun listToDataJson(list: List<T>): String

    @TypeConverter
    fun dataJsonToList(data: String): List<T>
}