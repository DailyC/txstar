package com.dailyc.txstar.developer.cloud.resource.server.domain.dictionary

import com.dailyc.txstar.developer.cloud.api.domain.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * 应用字典
 * 字典主要用于处理应用配置项，这种配置一般是对租户的配置
 * @author Wang Weiwei / email : wangweiwei12@jd.com
 * @since 2019-03-22
 * @version 17.8.0
 */
@Entity
@Table(name="cloud_resource_dictionary")
class Dictionary(
        /**
         * 字典所有者，字典所有者分为系统字典和用户字典。
         * 对于两者之间的关系： 用户字典一定要属于某个系统字典的类别之下
         * */
        var appId:String ?= null,
        /**
         * 字典编码
         * */
        @Column(name = "config_name")
        var type:String ?= null,
        /**
         * 字典值
         * */
        @Column(name = "config_value")
        var value:String ?= null,
        /**
         * 配置状态：DOWN-下架，UP-上架
         * */
        var status:String = "UP"
) : BaseEntity() {

}