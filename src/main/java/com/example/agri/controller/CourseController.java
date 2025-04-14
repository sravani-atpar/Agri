package com.example.agri.controller;

import com.example.agri.Entity.Course;
import com.example.agri.Repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseRepo courseRepo;
    public CourseController(CourseRepo courseRepo){this.courseRepo = courseRepo;}
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        return new ResponseEntity<>(courseRepo.save(course), HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(courseRepo.findAll(), HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id){
        Optional<Course> course = courseRepo.findById(id);
        if(course.isPresent()){
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course){
        Optional<Course> course1 = courseRepo.findById(id);
        if(course1.isPresent()){
            return new ResponseEntity<>(courseRepo.save(course), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id){
        Optional<Course> course = courseRepo.findById(id);
        if(course.isPresent()){
            courseRepo.deleteById(id);
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
