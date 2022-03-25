package com.froyout.froysourceExample.data.model

import com.froyout.froysourceExample.base.ErrorResponse
import com.google.gson.annotations.SerializedName

class CatErrorResponse: ErrorResponse() {
    @SerializedName("code")
    override var code: Int = 0

    @SerializedName("message")
    override var message: String = ""
}