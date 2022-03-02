package com.example.employee.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "salary", nullable = false)
    private long salary;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "image")
    private String image;

}
