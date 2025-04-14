package com.example.agri.service.impl;

import com.example.agri.Entity.Course;
import com.example.agri.Repository.CourseRepo;
import com.example.agri.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
@Autowired
    private CourseRepo courseRepo;
    public Course save(Course course) {
        course.setCreatedBy("user");
        course.setLastModifiedBy("user");
        course.setCreatedDate(Timestamp.from(Instant.now()));
        course.setLastModifiedDate(Timestamp.from(Instant.now()));
        return courseRepo .save(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        courseRepo.deleteById(id);

    }
}
