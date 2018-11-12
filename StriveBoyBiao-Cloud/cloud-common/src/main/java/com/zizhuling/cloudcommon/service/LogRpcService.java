package com.zizhuling.cloudcommon.service;

import com.zizhuling.cloudcommon.dto.LogDO;
import com.zizhuling.cloudcommon.intercepter.FeignIntercepter;
import com.zizhuling.cloudcommon.utils.R;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-base", configuration = FeignIntercepter.class)
public interface LogRpcService {
    @Async
    @PostMapping("log/save")
    R save(LogDO logDO);
}
