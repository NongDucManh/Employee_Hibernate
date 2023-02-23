package com.example.employee_hibernate.repository;

import com.example.employee_hibernate.model.Employee;

import java.util.ArrayList;

public interface IEmployeeRepository {
    ArrayList<Employee> findAll();
    Employee saveEmployee(Employee employee);
    Employee deleteEmployee(int id);
    Employee findEmployeeById(int id);
}
