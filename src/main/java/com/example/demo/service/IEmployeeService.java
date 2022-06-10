package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee saveEmp(Employee employee);

    List<Employee> getAllEmpRecord();

    Address updateAddr(Integer id, Address address);

    String removeEmployee(Integer id);

    Employee updateAllEmployeeRecords(Integer id, Employee employee);

}
