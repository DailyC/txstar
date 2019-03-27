package com.dailyc.txstar.developer.cloud.resource.server.domain.dictionary

import com.dailyc.txstar.developer.cloud.api.domain.cloud.CloudRole
import com.dailyc.txstar.developer.cloud.api.domain.entity.DeleteEntity
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
         * 字典所属者类型
         * 字典所属这有三种类型：
         * 平台、应用、开发者
         */
        var cloudRole: CloudRole ?= CloudRole.DEVELOPER,
        /**
         * 字典所有者，平台的所有者是 PLATFORM，应用自身的所有者是 APPLYCATION
         * */
        var owner:String ?= null,
        /**
         * 字典所有系统编号，平台的编号是 PLATFORM
         * */
        var appId:String ?= null,
        /**
         * 字典编码
         * */
        var configName:String ?= null,
        /**
         * 字典值
         * */
        var configValue:String ?= null,
        /**
         * 备注
         * */
        var remark:String ?= "",
        /**
         * 字典所属类（父级字典）
         * */
        var parent: String ?= null
) : DeleteEntity() {

}