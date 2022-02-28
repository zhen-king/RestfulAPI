package com.example.student.Controller;

import com.example.student.DAO.StudentRepository;
import com.example.student.Entity.StudentEntity;
import com.example.student.Exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentRestController {
    private final StudentRepository repository;

    @Autowired
    StudentRestController(StudentRepository repository){
        this.repository = repository;
    }

    @GetMapping("/students")
    List<StudentEntity> getAllStudents() {
        return repository.findAll();
    }

    @GetMapping("/students/{id}")
    StudentEntity getStudent(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
    }

    @PostMapping("/students")
    StudentEntity addStudent(@RequestBody StudentEntity newStudent) {
        return repository.save(newStudent);
    }

    @PutMapping("/students/{id}")
    StudentEntity updateStudent(@RequestBody StudentEntity newStudent, @PathVariable Long id) {
        return repository.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setGrade(newStudent.getGrade());
                    return repository.save(student);
                })
                .orElseGet(() -> {
                    newStudent.setId(id);
                    return repository.save(newStudent);
                });
    }

    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
