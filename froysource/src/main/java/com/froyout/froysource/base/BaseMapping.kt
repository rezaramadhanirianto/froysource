package com.froyout.froysourceExample.base

import com.froyout.froysourceExample.FroyError
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException

open class BaseMapping {
    private val gson = Gson()

    fun <T: ErrorResponse> convertErrorBody(throwable: Throwable? = null, responseBody: ResponseBody? = null, classes: Class<T>): ErrorResponse? {
        return try {
            if(responseBody != null){
                gson.fromJson(responseBody.string(), classes)
            }else{
                mapException(throwable, classes)
            }
        } catch (exception: Exception) {
            FroyError(0,  exception.message ?: "")
        }
    }

    private fun <T: ErrorResponse> mapException(exception: Throwable?, classes: Class<T>): ErrorResponse?{
        try{
            when(exception){
                is HttpException -> {
                    exception.response()?.errorBody()?.let {
                        return gson.fromJson(it.string(), classes)
                    }
                }
                else -> {
                    return FroyError(0, exception?.message ?: "")
                }
            }
            return null
        }catch (e: java.lang.Exception){
            return FroyError(0, "Error parsing data error")
        }
    }
}