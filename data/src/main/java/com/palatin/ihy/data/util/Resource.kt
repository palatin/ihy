package com.palatin.ihy.data.util

/**
 * Represents possible data/request statuses
 */
sealed interface Resource<out T> {

    interface DataHolder<out T> {
        val data: T
    }

    /**
     * Represents success data state and contains result as [data] field
     */
    data class Success<out T>(override val data: T) : Resource<T>, DataHolder<T>

    /**
     * Represents failure and contains result as [throwable] field
     */
    data class Failure(val throwable: Throwable) : Resource<Nothing>

    /**
     * Represents the beginning of request
     */
    object Loading : Resource<Nothing>
}

/**
 * Applies [transformer] in case [this] resource contains data state
 * or returns the state as it is
 */
inline fun <T, R> Resource<T>.transform(transformer: (T) -> R): Resource<R> {
    return when(this) {
        is Resource.Success -> Resource.Success(transformer(data))
        is Resource.Failure -> this
        Resource.Loading -> Resource.Loading
    }
}

/**
 * @return whether [this] is [Resource.Success]
 */
fun Resource<*>.isSuccess() = this is Resource.Success

/**
 * @return [T] or null if this represents state with no data
 */
fun <T> Resource<T>.dataOrNull(): T? = when (this) {
    is Resource.Success -> data
    else -> null
}

fun <T> Result<T>.toResource() =
    fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Failure(it) }
    )