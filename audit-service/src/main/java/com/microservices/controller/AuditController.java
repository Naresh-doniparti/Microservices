package com.microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuditController {

    @GetMapping("/audit")
    public String getAudit(){
        return "Message from audit controller";
    }
}
