package com.example.employee.Service;

import com.example.employee.Entity.Employee;
import com.example.employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllStudents() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream()
                .map(e -> new Employee(e.getId(),e.getEmployee_name(),e.getEmployee_salary(),e.getEmployee_age(), e.getProfile_image()))
                .collect(Collectors.toList());
    }

    @Override
    public Employee findById(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee currentEmployee) {
       Employee employee = employeeRepository.saveAndFlush(
               new Employee(
                       currentEmployee.getId(),
                       currentEmployee.getEmployee_name(),
                       currentEmployee.getEmployee_salary(),
                       currentEmployee.getEmployee_age(),
                       currentEmployee.getProfile_image()));
       return employee;
    }

    @Override
    public Employee deleteById(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employeeRepository.deleteById(id);
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee currentEmployee) {
        Employee employee = employeeRepository.save(
                new Employee(
                        currentEmployee.getId(),
                        currentEmployee.getEmployee_name(),
                        currentEmployee.getEmployee_salary(),
                        currentEmployee.getEmployee_age(),
                        currentEmployee.getProfile_image()));
        return employee;
    }

    @Override
    public List<Employee> findAgeGreater(int age) {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream()
                .map(e -> new Employee(e.getId(),e.getEmployee_name(),e.getEmployee_salary(),e.getEmployee_age(), e.getProfile_image()))
                .filter(e -> e.getEmployee_age() > age)
                .collect(Collectors.toList());
    }


}
