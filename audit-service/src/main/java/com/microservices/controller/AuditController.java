package com.microservices.controller;

import com.microservices.model.TestTable;
import com.microservices.repository.TestTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class AuditController {

    @Autowired
    private TestTableRepository testTableRepository;

    @GetMapping("/audit/all")
    public ResponseEntity getAll() {
        Iterable<TestTable> all = testTableRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/audit")
    public String getAudit() {
        return "Message from audit controller";
    }
}
