package com.froyout.froysource

import com.froyout.froysource.base.ErrorResponse

class FroyError(
    override var code: Int = 0,
    override var message: String = ""
): ErrorResponse()