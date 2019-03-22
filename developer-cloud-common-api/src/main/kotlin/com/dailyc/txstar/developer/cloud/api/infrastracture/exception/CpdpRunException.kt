package com.dailyc.txstar.developer.cloud.api.infrastracture.exception

import com.dailyc.txstar.developer.cloud.api.infrastracture.rest.ResponseCode
import org.apache.commons.lang3.StringUtils

/**
 * 开发者异常
 */
data class CpdpRunException(
        var code: String,
        var msg: String,
        /**
         * 开发者原始报文
         * */
        var origin: String? = null
) : RuntimeException ("$code | $msg"){
    init {
        if (StringUtils.isNotEmpty(origin)){
            msg += "| origin = $origin"
        }
    }

    constructor() : this("", "")

    constructor(responseCode: ResponseCode) : this(responseCode.code, responseCode.msg) {
        this.code = responseCode.code
        this.msg = responseCode.msg
    }

    constructor(responseCode: ResponseCode, origin: String? = null) : this(responseCode.code, responseCode.msg, origin)
    fun toJsonString(): String {
        return "{\"code\":\"" + this.code + "\",\"msg\":\"" + this.msg + "\"}"
    }
}
