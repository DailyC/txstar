package com.dailyc.txstar.developer.cloud.resource.server.domain.dictionary

import org.springframework.stereotype.Service

/**
 * 词典领域服务
 * 领域服务中大部分都是基于查询的服务功能
 * @author Wang Weiwei / email : wangweiwei12@jd.com
 * @since 2019-03-26
 * @version 17.8.0
 */
@Service
class DictionaryDS(
         dictionaryRepository: DictionaryRepository
) {
    fun findByOwner(){}
}