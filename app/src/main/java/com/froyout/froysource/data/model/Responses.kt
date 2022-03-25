package com.froyout.froysource.data.model

import com.google.gson.annotations.SerializedName
import com.froyout.froysource.base.BaseResponse

class Responses<T>: BaseResponse<T>() {
    @SerializedName("data")
    override val results: T? = null
}