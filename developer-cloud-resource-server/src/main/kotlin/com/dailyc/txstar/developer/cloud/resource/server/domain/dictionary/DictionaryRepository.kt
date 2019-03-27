package com.dailyc.txstar.developer.cloud.resource.server.domain.dictionary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * 开发者平台词典持久化组件
 * @author Wang Weiwei / email : wangweiwei12@jd.com
 * @since 2019-03-26
 * @version 17.8.0
 */
@Repository
interface DictionaryRepository : JpaRepository<Dictionary, Long> {
}