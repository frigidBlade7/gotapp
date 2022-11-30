package com.zatec.gotapp.core.data

interface DataMapper<R,D,U> {

    fun toData(): D
    fun toUi(): U
}