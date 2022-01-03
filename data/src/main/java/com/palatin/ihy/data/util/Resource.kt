package com.palatin.ihy.data.util

sealed interface Resource<out T> {

    data class Success<out T>(val data: T) : Resource<T>

    data class Failure(val throwable: Throwable) : Resource<Nothing>

    object Loading : Resource<Nothing>
}