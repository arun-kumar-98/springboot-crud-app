package com.example.demo.service.impl;

import com.example.demo.entity.Address;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Role;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IAddressRepository;
import com.example.demo.repository.IEmployeeRepository;
import com.example.demo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository repository;
    @Autowired
    private IAddressRepository addressRepository;


    @Override
    public Employee saveEmp(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmpRecord() {
        return repository.findAll();
    }


    @Override
    public Address updateAddr(Integer id, Address address) throws ResourceNotFoundException {
        Address address2 = addressRepository.findById(id).get();
        if (addressRepository.existsById(id) && id != null) {
            address2.setLine1(address.getLine1());
            address2.setLine2(address.getLine2());
            address2.setPincode(address.getPincode());
            address2.setState(address.getState());
            addressRepository.save(address2);
        } else {
            new ResourceNotFoundException("given is not exist in Record" + address.getPincode());
        }
        return address2;
    }

    @Override
    public String removeEmployee(Integer id) {
        Employee employee = repository.findById(id).get();
        if (repository.existsById(id) && id != null) {
            repository.delete(employee);
        }
        return "employee removed from database with employee id:  " + employee.getEid();
    }

    @Override
    public Employee updateAllEmployeeRecords(Integer id, Employee employee) {
        Employee employee1 = repository.findById(id).get();
        Collection<Address> empCollection = new ArrayList<>();
        if (repository.existsById(id) && id != null) {
            employee1.setEmail(employee.getEmail());
            employee1.setEmpName(employee.getEmpName());
            employee1.setPassword(employee.getPassword());
            Collection<Address> addressCollection = employee1.getAddresses();
            Collection<Address> addresses = employee.getAddresses();
            Address address1 = new Address();
            for (Address address : addresses) {
                address1.setLine1(address.getLine1());
                address1.setLine2(address.getLine2());
                address1.setPincode(address.getPincode());
                address1.setState(address.getState());
                empCollection.add(address1);

            }
            Role role = employee.getRole();
            employee1.setRole(role);
            employee1.setAddresses(empCollection);
        }

        return repository.save(employee1);
    }
}
