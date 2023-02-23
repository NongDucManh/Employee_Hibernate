package com.example.employee_hibernate.service;

import com.example.employee_hibernate.model.Employee;

import java.util.ArrayList;

public interface IEmployeeService {
    ArrayList<Employee> getAllEmployee();
    Employee saveEmployee(Employee employee);
    Employee deleteEmployee(int id);
    Employee getEmployee(int id);
}
