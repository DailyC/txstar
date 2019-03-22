package com.dailyc.txstar.developer.cloud.api.infrastracture.rest

import com.alibaba.fastjson.JSONObject
import java.io.Serializable

/**
 * 前后台交互公共响应报文
 * @author wangweiwei
 * @since 2018-09-05
 * @version 1.2.1
 */
data class RestResult<E>(
        val code: String,
        val msg: String,
        var data: E ?= null
) : Serializable {

    /**
     * 无参构造方法，默认创建成功状态的报文
     * */
    constructor(data: E ?) : this(ResponseCode.SUCCESS.code, ResponseCode.SUCCESS.msg, data)

    constructor(responseCode: ResponseCode) : this(responseCode.code, responseCode.msg)

    constructor(responseCode: ResponseCode, data: E ?) : this(responseCode.code, responseCode.msg, data)

    fun isSuccess() = "00000" == code
    companion object {
        private const val serialVersionUID = 1L
        /**
         * 创建默认为异常的报文
         * */
        fun <E> failureResult(data: E ?= null): RestResult<E> = RestResult(ResponseCode.SYSTEM_ERROR, data)

        fun  fromJSONString(jsonString:String) : RestResult<String> {
            val json = JSONObject.parseObject(jsonString)
            return RestResult(json.getString("code"), json.getString("msg"), json.getString("data"))
        }
    }

    fun toJSONString():String = JSONObject.toJSONString(this)
    fun jsonData(): JSONObject {
        if (data == null || data == "")  return JSONObject()
        return if (data is String) JSONObject.parseObject(data as String)
        else JSONObject.toJSON(data) as JSONObject
    }
}
