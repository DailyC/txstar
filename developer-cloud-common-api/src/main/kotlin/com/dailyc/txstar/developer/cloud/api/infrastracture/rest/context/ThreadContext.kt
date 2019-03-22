package com.jd.jr.cpdp.common.infrastracture.rest.context

import com.alibaba.fastjson.JSONObject

/** 线程变量
 * @author wangweiwei
 * @version 14.1.0
 * @since 2018/8/29
 */
object ThreadContext {
    private val param = ThreadLocal<JSONObject>()

    fun put(key: String, value: String) {
        getParam()[key] = value
    }

    operator fun get(key: String): String {
        return getParam().getString(key) ?: ""
    }

    fun clear() {
        param.remove()
    }

    private fun getParam(): JSONObject {
        if (param.get() == null) {
            val `object` = JSONObject()
            param.set(`object`)
            return `object`
        }
        return param.get()
    }
}
