package me.aprilian.mynews.core.data

import android.content.res.Resources
import me.aprilian.mynews.R

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        EMPTY
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                message
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }

        fun <T> empty(): Resource<T> {
            return Resource(Status.EMPTY, null, null)
        }

        fun isNoInternetConnection(error: String?): Boolean{
            return error?.contains(Resources.getSystem().getString(R.string.api_error_no_hostname)) ?: false
        }

        fun getErrorMessageToUser(message: String?): String {
            return if (isNoInternetConnection(message))
                Resources.getSystem().getString(R.string.api_error_no_internet_connection)
            else
                Resources.getSystem().getString(R.string.api_error_request_failed)
        }
    }
}