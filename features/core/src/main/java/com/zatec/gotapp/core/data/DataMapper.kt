package com.zatec.gotapp.core.data


/**
 * Data mapper interface for responses that can be stored in room
 * and displayed in a fragment
 *
 * @param R data type for the response object
 * @param D data type of the response stored in the room db
 * @param U data type of the response to display on ui
 * @constructor Create empty Data mapper
 */
interface DataMapper<R,D,U> {

    /**
     * To data
     *
     * @return
     */
    fun toData(): D

    /**
     * To ui
     *
     * @return
     */
    fun toUi(): U
}