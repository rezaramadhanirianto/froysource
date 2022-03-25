package com.froyout.froysourceExample

import com.froyout.froysourceExample.base.ErrorResponse

class FroyError(
    override var code: Int = 0,
    override var message: String = ""
): ErrorResponse()