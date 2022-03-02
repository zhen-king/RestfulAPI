package com.example.employee.service;

import com.example.employee.dao.EmployeeRespository;
import com.example.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    EmployeeRespository respository;

    @Autowired
    public EmployeeService(EmployeeRespository respository){
        this.respository = respository;
    }

    public Employee saveEmployee(Employee employee){
        return respository.save(employee);
    }

    public List<Employee> saveEmployees(List<Employee> employees) {
        return respository.saveAll(employees);
    }

    public Employee getEmployeeById(int id){
        return respository.findById(id).orElse(null);
    }


    public String deleteEmployee(int id){
        respository.deleteById(id);
        return "Employee Removed !!" + id;
    }

    public Employee updateProduct(Employee employee){
        Employee currentEmployee=respository.findById(employee.getId()).orElse(null);
        currentEmployee.setName(employee.getName());
        currentEmployee.setImage(employee.getImage());
        currentEmployee.setAge(employee.getAge());
        currentEmployee.setSalary(employee.getSalary());
        return respository.save(currentEmployee);
    }

    public List<Employee> getEmployees() {
        return respository.findAll();
    }

    public List<Employee> getAgeGreater(int age) {

        return respository.getAgeGreater(age);
    }
}
