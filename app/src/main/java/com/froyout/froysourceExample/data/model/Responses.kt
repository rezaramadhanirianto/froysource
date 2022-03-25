package com.froyout.froysourceExample.data.model

import com.google.gson.annotations.SerializedName
import com.froyout.froysourceExample.base.BaseResponse

class Responses<T>: BaseResponse<T>() {
    @SerializedName("data")
    override val results: T? = null
}