package com.microservices.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "audit-service")
public interface AuditServiceFeignClient {

    @GetMapping(value = "/audit")
    public String getAudit();
}
