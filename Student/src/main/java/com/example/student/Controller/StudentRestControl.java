package com.example.student.Controller;

import com.example.student.DAO.StudentRepository;
import com.example.student.Entity.StudentEntity;
import com.example.student.Exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;



public class StudentRestControl {
        private static List<StudentEntity> studentList = new ArrayList<StudentEntity>();
        private static Map<Integer, List<StudentEntity>> newStudentRepo = new HashMap<>();


    static {
            StudentEntity student1 = new StudentEntity();
            student1.setId(1L);
            student1.setName("K");
            student1.setGrade(78);

            StudentEntity student2 = new StudentEntity();
            student2.setId(2L);
            student2.setName("K");
            student2.setGrade(78);

            StudentEntity student3 = new StudentEntity();
            student3.setId(3L);
            student3.setName("K");
            student3.setGrade(78);

            studentList.add(student1);
            studentList.add(student2);
            studentList.add(student3);


            for (StudentEntity student : studentList){
                newStudentRepo.put(student.getGrade(), studentList);
            }

    }



    @GetMapping
    public ResponseEntity<Object> getProduct() {
            return new ResponseEntity<>(newStudentRepo.values(), HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        newStudentRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody List<StudentEntity> product) {
        newStudentRepo.remove(id);
        product.setId(id);
        newStudentRepo.put(id, product);
        return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        newStudentRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }







}
