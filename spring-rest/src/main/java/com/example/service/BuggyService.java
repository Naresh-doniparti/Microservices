package com.example.service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BuggyService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @HystrixCommand(fallbackMethod = "fallBackMethodForBuggyService")
    public String sendItToExternalService(Employee employee){

        employeeRepository.save(employee);
        return "Saved the details successfully";
    }

    public String fallBackMethodForBuggyService(Employee employee){
        return "Systems are currently under maintenance, but we are unable to process now !";
    }
}
