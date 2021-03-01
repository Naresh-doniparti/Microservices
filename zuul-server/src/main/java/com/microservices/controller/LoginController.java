package com.microservices.controller;

import com.microservices.auth.beans.LoginRequest;
import com.microservices.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        String token = loginService.login(loginRequest.getUsername(),
                loginRequest.getPassword());
        HttpHeaders headers = new HttpHeaders();
        List<String> headersList = new ArrayList<>();
        List<String> exposeList = new ArrayList<>();
        addAllowedHeaders(headers, headersList);
        addExposedHeaders(headers, exposeList);
        headers.set("Authorization", token);
        return new ResponseEntity<String>(token, headers, HttpStatus.CREATED);
    }


    private void addExposedHeaders(HttpHeaders headers, List<String> exposeList) {
        exposeList.add("Authorization");
        headers.setAccessControlExposeHeaders(exposeList);
    }

    private void addAllowedHeaders(HttpHeaders headers, List<String> headersList) {
        headersList.add("Content-Type");
        headersList.add("Accept");
        headersList.add("Authorization");
        headers.setAccessControlAllowHeaders(headersList);
    }

}
