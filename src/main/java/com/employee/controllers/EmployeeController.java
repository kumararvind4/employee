package com.employee.controllers;

import com.employee.model.Employee;
import com.employee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // READ: View all employees
    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeRepository.findAll());
        return "index";
    }

    // CREATE: Show the form to add a new employee
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    // CREATE/UPDATE: Handle saving employee data
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    // UPDATE: Show the form to edit an existing employee
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    // DELETE: Delete an employee
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }
}
