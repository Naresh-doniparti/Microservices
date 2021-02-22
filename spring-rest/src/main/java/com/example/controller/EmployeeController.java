package com.example.controller;

import com.example.NoRecordFoundException;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RefreshScope
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Value("${message}")
    private String message;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> all = employeeRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return ResponseEntity.ok()
                .header("message", message)
                .body(employee.orElseThrow(() -> new NoRecordFoundException(id)));
    }

    @PostMapping
    public void create(@RequestBody Employee employee) {
        employeeRepository.save(employee);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Employee> update(@PathVariable long id, @RequestBody HashMap<String, Object> map) {
        Employee employee =  employeeRepository.findById(id).orElseThrow(() -> new NoRecordFoundException(id));
        map.forEach((s, o) -> {
            switch (s){
                case "name": employee.setName((String) o); break;
                case "salary": employee.setSalary((double) o); break;
            }
        });
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(employee);
    }
}
