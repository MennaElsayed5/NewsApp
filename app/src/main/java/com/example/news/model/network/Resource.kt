package com.example.news.model.network


sealed class Resource<out T>(val status: Status, val data: T?, val msg: String?) {

    data class Loading<out R>(val _data: R, val isLoading : Boolean) : Resource<R>(
        status = Status.LOADING,
        data = _data,
        msg = null
    )

    data class Success<out R>(val _data: R) : Resource<R>(
        status = Status.SUCCESS,
        data = _data,
        msg = null
    )

    data class Error<out R>(val _msg: String) : Resource<R>(
        status = Status.ERROR,
        data = null,
        msg = _msg
    )
}

