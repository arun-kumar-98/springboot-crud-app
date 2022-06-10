package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.entity.Employee;
import com.example.demo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService service;

    @PostMapping("/save")
    public ResponseEntity<Employee> storeEmp(@RequestBody Employee employee) {
        return ResponseEntity.ok(service.saveEmp(employee));
    }

    @PostMapping("/get")
    public ResponseEntity<List<Employee>> empDetails() {
        return ResponseEntity.ok(service.getAllEmpRecord());
    }

//    @PostMapping("/update/{id}")
//    public ResponseEntity<Address> updateExistingEmpAddress(@PathVariable Integer id, @RequestBody Address address) {
//        return ResponseEntity.ok(service.updateAddress(id, address));
//    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Address> updateEmpDetails(@PathVariable Integer id, @RequestBody Address address) {
        return ResponseEntity.ok(service.updateAddr(id, address));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable Integer id) {
        return ResponseEntity.ok(service.removeEmployee(id));

    }
}
