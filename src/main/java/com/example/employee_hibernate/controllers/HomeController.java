package com.example.employee_hibernate.controllers;


import com.example.employee_hibernate.model.Employee;
import com.example.employee_hibernate.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/employees")
public class HomeController {
    @Autowired
    private IEmployeeService employeeService;
    @GetMapping
    public ModelAndView showEmployees() {
        ModelAndView modelAndView = new ModelAndView("list");
        ArrayList<Employee> products = employeeService.getAllEmployee();
        if (products.isEmpty()) {
            modelAndView.addObject("message", "No employees!");
            modelAndView.addObject("color", "red");
        }
        modelAndView.addObject("employees", products);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("list");
        Employee employee = employeeService.deleteEmployee(id);
        if (employee == null) {
            modelAndView.addObject("message", "Id invalid!");
            modelAndView.addObject("color", "red");
        }
        ArrayList<Employee> employees = employeeService.getAllEmployee();
        modelAndView.addObject("employees", employee);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Employee employee = employeeService.getEmployee(id);
        if (employee!= null) {
            modelAndView.addObject("product", employee);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createEmployee(Model model) {
        ModelAndView modelAndView = new ModelAndView("create");
        model.addAttribute("employee", new Employee());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute Employee employee) {
        ModelAndView modelAndView = new ModelAndView("create");
        Employee productCreate = employeeService.saveEmployee(employee);
        if (productCreate != null) {
            modelAndView.addObject("message", "Create successfully!");
        }
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Employee employee = employeeService.getEmployee(id);
        if (employee != null) {
            modelAndView.addObject("employee", employee);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView edit(@ModelAttribute Employee employee, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        employee.setId(id);
        Employee EmployeeEdit = employeeService.saveEmployee(employee);
        if (EmployeeEdit != null) {
            modelAndView.addObject("message", "Update successfully!");
        }
        return modelAndView;
    }

}
