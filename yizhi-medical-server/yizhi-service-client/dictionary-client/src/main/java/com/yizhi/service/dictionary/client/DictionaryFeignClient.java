package com.yizhi.service.dictionary.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-dictionary")
@Repository
public interface DictionaryFeignClient {

    //根据dictionaryCode和value查询
    @GetMapping("/admin/dictionary/name/{dictionaryCode}/{value}")
    public String getName(@PathVariable("dictionaryCode") String dictionaryCode, @PathVariable("value") String value);

    //根据value查询
    @GetMapping("/admin/dictionary/name/{value}")
    public String getName(@PathVariable("value") String value);
}
