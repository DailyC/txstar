package com.dailyc.txstar.developer.cloud.api.domain.event

import org.springframework.context.ApplicationEvent

/**
 * 增删改查事件
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/11/1
 * @version 1.0.0
 */
class CurdEvent<T:Any> (
        var operation: CurdOperation = CurdOperation.UPDATE,
        var from:T
):ApplicationEvent(from){
    var sourceClassName:String = from::class.simpleName!!
}

/**
 * 数据库操作事件类型
 * */
enum class CurdOperation{
    INSERT,
    UPDATE,
    DELETE,
}