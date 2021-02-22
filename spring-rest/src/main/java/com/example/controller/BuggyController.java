package com.example.controller;

import com.example.model.Employee;
import com.example.service.BuggyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
@RequestMapping("/buggy")
public class BuggyController {

    @Autowired
    private BuggyService buggyService;

    @PostMapping
    public void saveExternally(@RequestBody Employee employee){
        IntStream.range(1, 100).forEach(value -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buggyService.sendItToExternalService(employee);
        });
    }

}
