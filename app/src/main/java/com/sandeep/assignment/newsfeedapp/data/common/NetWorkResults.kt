package com.sandeep.assignment.newsfeedappdemo.data.common

sealed class NetWorkResults<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T): NetWorkResults<T>(data)
    class Error<T>(message: String, data: T? = null): NetWorkResults<T>(data, message)
    class Loading<T> : NetWorkResults<T>()
}