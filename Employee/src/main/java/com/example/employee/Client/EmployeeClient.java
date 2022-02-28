package com.example.employee.Client;

import com.example.employee.Entity.Employee;
import com.example.employee.Entity.EmployeeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class EmployeeClient {

    public static void main(String[] args) {
        EmployeeClient employeeClient = new EmployeeClient();
//        System.out.println("Get all employee");
//        employeeClient.getEmployee();
        System.out.println("Get all employee age greater than 60");
        employeeClient.getAgeGreater(60);
    }




    public List<Employee> getEmployee() {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<EmployeeList> response = rt.getForEntity("http://dummy.restapiexample.com/api/v1/employees", EmployeeList.class);
        List<Employee> employees = response.getBody().getData();
        assert employees != null;
       //employees.forEach(System.out::println);
        return employees;

    }

    public List<String> getAgeGreater(int age) {
        List<Employee> employees = getEmployee();
        List<String> nameList = new ArrayList<>();
        nameList = employees.stream().filter(employee -> employee.getEmployee_age()>age)
                .map(employee -> employee.getEmployee_name())
                .collect(Collectors.toList());
        nameList.forEach(System.out::println);
        return nameList;
    }
}
