package com.dailyc.txstar.developer.cloud.api.domain.entity

import com.alibaba.fastjson.JSONObject
import com.dailyc.txstar.developer.cloud.api.domain.event.CurdEvent
import com.dailyc.txstar.developer.cloud.api.domain.event.CurdOperation
import com.dailyc.txstar.developer.cloud.api.infrastracture.event.EventBusUtil
import com.dailyc.txstar.developer.cloud.api.infrastracture.exception.CpdpRunException
import com.dailyc.txstar.developer.cloud.api.infrastracture.rest.ResponseCode
import org.springframework.context.ApplicationEvent
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import javax.persistence.*

/**
 * 基础实体
 * 基础实体中包含 id ，createdDate moodifiedTime 三个基本属性
 *
 * 如果基础实体是聚合跟，那么还有事件发布的能力
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/10/8
 * @version 2.0.0
 */
@MappedSuperclass
open class BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    var id:Long? = null
    @CreatedDate
    var createdTime: Date = Date()
    @LastModifiedDate
    var modifiedTime: Date = Date()

    /**
     * 新增数据
     * */
    open fun save() {
        validate()
        post(CurdEvent(CurdOperation.INSERT, from = this))
    }


    /**
     * 更新数据
     * */
    open fun update(){
        if (id == null) throw CpdpRunException(ResponseCode.DATA_BASE_ERROR)
        post(CurdEvent(CurdOperation.UPDATE, from = this))
    }

    open fun delete(){
        if (id == null) throw CpdpRunException(ResponseCode.DATA_BASE_ERROR)
        post(CurdEvent(CurdOperation.DELETE, from = this))
    }

    /**
     * 发布信贷平台自测事件
     */
    fun <T : ApplicationEvent> post(event: T) {
        EventBusUtil.post(event)
    }

    /**
     * 数据校验方法
     * */
    open fun validate(){}

    fun toJSONString(): String = JSONObject.toJSONString(this)
    fun toJSON(): JSONObject = JSONObject.toJSON(this) as JSONObject
}

/**
 * 可删除实体
 * 增加 deleted 字段，默认值为 0 ，代表可用状态
 * */
@MappedSuperclass
open class DeletedEntity: BaseEntity(){
    @Column
    var deleted: String = "0"

    fun canUse() = deleted == "0"

    /**
     * 将数据标记为删除，并不真正删除数据
     * */
    open fun deleteMark(){
        if (id == null) throw CpdpRunException(ResponseCode.SYSTEM_PARAM_ERROR)
        deleted = "1"
        post(CurdEvent(CurdOperation.UPDATE, from = this))
    }
}