package com.example.employee.dto;

import com.example.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTO {
    private String status;
    private List<Employee> data;
    private String message;
}
