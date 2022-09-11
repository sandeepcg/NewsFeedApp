package com.sandeep.assignment.newsfeedapp.data.common

import com.sandeep.assignment.newsfeedapp.utils.EMPTY_STR

/**
 * Class for handing Network fetch results
 */

sealed class NetWorkResults<T>(
    val data: T? = null,
    val message: String = EMPTY_STR
) {
    class Success<T>(data: T,message: String,): NetWorkResults<T>(data, message)
    class Error<T>(message: String, data: T? = null): NetWorkResults<T>(data, message)
    class Loading<T> : NetWorkResults<T>()
}