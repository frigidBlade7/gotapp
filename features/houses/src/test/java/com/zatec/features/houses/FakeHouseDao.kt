package com.zatec.features.houses

import androidx.paging.PagingSource
import com.zatec.features.houses.api.HouseResponse
import com.zatec.features.houses.data.HouseDao
import com.zatec.features.houses.persistence.HouseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeHouseDao(private val houseList: List<HouseResponse>? = listOf()): HouseDao {
    override fun insert(vararg houses: HouseData) {
        TODO("Not yet implemented")
    }

    override fun getHouses(): Flow<List<HouseData>> = flow {
            emit(houseList?.map { it.toData() }?: listOf())
        }

    override fun getPagedHouses(): PagingSource<Int, HouseData> {
        TODO("Not yet implemented")
    }

    override fun getHouseById(houseId: String): Flow<HouseData?> = flow {
        emit(houseList?.first()?.toData())
    }

}