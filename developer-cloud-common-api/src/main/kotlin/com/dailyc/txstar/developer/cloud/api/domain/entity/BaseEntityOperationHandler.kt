package com.jd.jr.cpdp.common.domain.entity

import com.dailyc.txstar.developer.cloud.api.domain.entity.BaseEntity
import com.dailyc.txstar.developer.cloud.api.domain.event.CurdEvent
import org.springframework.context.event.EventListener
import org.springframework.data.jpa.repository.JpaRepository

/**
 * 基础聚合跟命令处理器
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/11/1
 * @version 1.0.0
 */
abstract class BaseEntityOperationHandler(
        private val repository: JpaRepository<BaseEntity, Long>
){
    /**
     * 要处理的类型名
     * */
    abstract var sourceClassName:String
    /**
     * 保存
     * */
    @EventListener(condition = "#event.operation == T(com.dailyc.txstar.developer.cloud.api.domain.event.CurdOperation).INSERT")
    fun save(event: CurdEvent<BaseEntity>){
        if(event.sourceClassName != sourceClassName) return
        repository.save(event.from)
    }

    /**
     * 更新
     * */
    @EventListener(condition = "#event.operation == T(com.dailyc.txstar.developer.cloud.api.domain.event.CurdOperation).UPDATE")
    fun update(event: CurdEvent<BaseEntity>){
        if(event.sourceClassName != sourceClassName) return
        event.from.takeIf { it.id != null }.apply {
            repository.save(this!!)
        }
    }

    /**
     * 删除
     * */
    @EventListener(condition = "#event.operation == T(com.dailyc.txstar.developer.cloud.api.domain.event.CurdOperation).DELETE")
    fun delete(event: CurdEvent<BaseEntity>){
        if(event.sourceClassName != sourceClassName) return
        event.from.takeIf { it.id != null }.apply {
            repository.delete(this!!)
        }
    }
}