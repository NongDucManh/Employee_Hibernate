package com.example.employee_hibernate.service.impl;

import com.example.employee_hibernate.model.Employee;
import com.example.employee_hibernate.repository.IEmployeeRepository;
import com.example.employee_hibernate.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public ArrayList<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.saveEmployee(employee);
    }

    @Override
    public Employee deleteEmployee(int id) {
        return employeeRepository.deleteEmployee(id);
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.findEmployeeById(id);
    }
}
