package com.zatec.gotapp.core.data

interface DataMapper<R,D,U> {

    fun <D> toData(): D
    fun <D> toUi(): U
}