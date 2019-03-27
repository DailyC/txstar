package com.dailyc.txstar.developer.cloud.api.domain.entity

import javax.persistence.EnumType
import javax.persistence.Enumerated

/**
 * 是否删除标记
 * @author Wang Weiwei / email : wangweiwei12@jd.com
 * @since 2019-03-26
 * @version 17.8.0
 */
open class DeleteEntity:BaseEntity() {
    /**
     * TRUE : 被删除
     * FALSE: 未被删除
     * */
    @Enumerated(EnumType.STRING)
    var deleted:BooleanValue ?= BooleanValue.FALSE

    /**
     * 删除实体资源
     * */
    override fun delete(){
        deleted = BooleanValue.TRUE
        super.delete()
    }
}