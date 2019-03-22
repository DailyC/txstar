package com.jd.jr.cpdp.common.infrastracture.rest.service

import com.alibaba.fastjson.JSONObject

/**
 * 开发者平台接口对象
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/10/8
 * @version 2.0.0
 */
class DevPlatformInterface(json: JSONObject){
    /**
     * 产品编号 xjd:现金贷
     * */
    var appId:String? by json
    /**
     * 接口类型 资方/平台
     * */
    var type:String? by json
    /**
     * 接口默认地址
     * */
    var url:String? by json
    /**
     * 接口描述信息
     * */
    var desc:String? by json
    /**
     * 接口中文名
     * */
    var name:String? by json
    /**
     * 接口名
     * */
    var interfaceName:String? by json
    /**
     * 是否核心接口
     * */
    var isCore:Boolean? by json

    fun isInvestor():Boolean = type == "investor"

    /**
     * 将是否是核心接口转化成字符串
     * */
    fun coreToStr():String =
        if (isCore != false) "CORE" else "ADVANCE"
}