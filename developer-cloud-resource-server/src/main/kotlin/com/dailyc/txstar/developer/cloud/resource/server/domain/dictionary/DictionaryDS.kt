package com.dailyc.txstar.developer.cloud.resource.server.domain.dictionary

import org.springframework.data.domain.*
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
         val dictionaryRepository: DictionaryRepository
) {

    /**
     * 查询app下所有配置
     * */
    fun findAllByApp(appId : String, page: Pageable = PageRequest.of(0, 100)) : Page<Dictionary>{
        val example = Example.of(Dictionary.ofApp(appId),
                ExampleMatcher.matching()
                        .withIgnoreCase("remark", "parent", "createdTime", "modifiedTime")
        )
        return dictionaryRepository.findAll(example, page)
    }
}