package com.employee.services;

import com.employee.model.Employee;
import com.employee.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmailService emailService;
   private final EmployeeProducer producer;

    public EmployeeService(EmployeeRepository repository,
                           EmailService emailService, EmployeeProducer producer) {
        this.repository = repository;
        this.emailService = emailService;
        this.producer = producer;
    }

    public Employee saveEmployee(Employee employee) {

        Employee savedEmployee = repository.save(employee);
        emailService.sendNotification(
                savedEmployee.getEmail());
        producer.sendMessage(savedEmployee.getEmail());
        return savedEmployee;
    }
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        Employee updatedEmployee = repository.save(existingEmployee);
        producer.sendMessage(updatedEmployee.getEmail());

        return updatedEmployee;
    }
}
