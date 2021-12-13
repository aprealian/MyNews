package me.aprilian.mynews.core.data

import com.google.gson.Gson
import me.aprilian.mynews.datasource.api.response.ErrorResponse
import okhttp3.ResponseBody
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return Resource.success(body)
        }

        return error(response.errorBody(), response.message(), response.code())
    }

    private fun <T> error(
        responseBody: ResponseBody?,
        message: String,
        code: Int? = null
    ): Resource<T> {
        return try {
            val bodyString = responseBody?.string()
            val error = Gson().fromJson(bodyString, ErrorResponse::class.java)
            Resource.error(error.message ?: "Something wrong..")
        } catch (e: Exception){
            Timber.e("remoteDataSource", message)
            Resource.error("Network call has failed for a following reason: $message")
        }
    }

}