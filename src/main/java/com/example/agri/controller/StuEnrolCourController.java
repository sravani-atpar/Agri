package com.example.agri.controller;

import com.example.agri.DTO.StuEnrolDTO;
import com.example.agri.Entity.StudentEnrolledCourses;
import com.example.agri.Repository.StudentEnrollCourRepo;
import com.example.agri.service.StudEnrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/studentenroll")
public class StuEnrolCourController {

    @Autowired
    private StudEnrolService studEnrolService;

    @Autowired
    StudentEnrollCourRepo studentEnrollCourRepo;

    public StuEnrolCourController(StudentEnrollCourRepo studentEnrollCourRepo) {
        this.studentEnrollCourRepo = studentEnrollCourRepo;
    }

    @PostMapping
    public ResponseEntity<StudentEnrolledCourses> saveStudentEnrolledCourses(@RequestBody StuEnrolDTO stuEnrolDTO) {
        return new ResponseEntity<>(studEnrolService.save(stuEnrolDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentEnrolledCourses>> getAllStudentEnrolledCourses() {
        return new ResponseEntity<>(studentEnrollCourRepo.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentEnrolledCourses> getStudentEnrolledCourses(@PathVariable int id) {
        Optional<StudentEnrolledCourses> studentEnrolledCourse = studentEnrollCourRepo.findById(Long.valueOf(id));
        if (studentEnrolledCourse.isPresent()) {
            return new ResponseEntity<>(studentEnrolledCourse.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentEnrolledCourses> updateStudentEnrolledCourses(
            @PathVariable Long  id,
            @RequestBody StudentEnrolledCourses updatedCourses) {
        Optional<StudentEnrolledCourses> studentEnrolledCourse = studentEnrollCourRepo.findById(Long.valueOf(id));

        if (studentEnrolledCourse.isPresent()) {
            StudentEnrolledCourses existingCourse = studentEnrolledCourse.get();
            StudentEnrolledCourses savedCourse = studentEnrollCourRepo.save(existingCourse);
            return new ResponseEntity<>(savedCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentEnrolledCourses(@PathVariable int id) {
        Optional<StudentEnrolledCourses> studentEnrolledCourse = studentEnrollCourRepo.findById(Long.valueOf(id));

        if (studentEnrolledCourse.isPresent()) {
            studentEnrollCourRepo.delete(studentEnrolledCourse.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}




