package com.zatec.features.houses

import androidx.paging.PagingSource
import com.zatec.features.houses.data.HouseDao
import com.zatec.features.houses.persistence.HouseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeHouseDao(private val houseList: MutableList<HouseData>? = mutableListOf()): HouseDao {
    override fun insert(vararg houses: HouseData) {
        for(house in houses){
            houseList?.add(house)
        }
    }

    override fun getHouses(): Flow<List<HouseData>> = flow {
            emit(houseList?: listOf())
        }

    override fun getPagedHouses(): PagingSource<Int, HouseData> {
        TODO("Not yet implemented")
    }

    override fun getHouseById(houseId: String): Flow<HouseData?> = flow {
        emit(houseList?.first())
    }

}