package com.example.agri.service;

import com.example.agri.Entity.Course;

import java.util.List;

public interface CourseService {
    Course save(Course course);
    List<Course> findAll();
    Course findById(Long id);
    void deleteById(Long id);

}
