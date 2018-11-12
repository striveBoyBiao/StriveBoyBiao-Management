package com.zizhuling.cloudadmin.rpc;

import com.zizhuling.cloudcommon.intercepter.FeignIntercepter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "course", configuration = FeignIntercepter.class)
public interface TestRpc {
    @GetMapping("/test/testRpc/test")
    String test();
}
