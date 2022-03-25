package com.froyout.froysource.data.model

import com.froyout.froysource.base.ErrorResponse
import com.google.gson.annotations.SerializedName

class CatErrorResponse: ErrorResponse() {
    @SerializedName("code")
    override var code: Int = 0

    @SerializedName("message")
    override var message: String = ""
}