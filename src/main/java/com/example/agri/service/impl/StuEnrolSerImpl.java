package com.example.agri.service.impl;

import com.example.agri.DTO.StuEnrolDTO;
import com.example.agri.Entity.Course;
import com.example.agri.Entity.StudentEnrolledCourses;
import com.example.agri.Entity.User;
import com.example.agri.Repository.CourseRepo;
import com.example.agri.Repository.StudentEnrollCourRepo;
import com.example.agri.Repository.UserRepository;
import com.example.agri.service.StudEnrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
@Service
public class StuEnrolSerImpl implements StudEnrolService {
    @Autowired
    private StudentEnrollCourRepo studentEnrollCourRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public StudentEnrolledCourses save(StuEnrolDTO stuEnrolDTO) {

        StudentEnrolledCourses studentEnrolledCourses = new StudentEnrolledCourses();
        studentEnrolledCourses.setAmount(stuEnrolDTO.getAmount());
        studentEnrolledCourses.setCreatedAt(Instant.now());

        Course course = courseRepo.findById(stuEnrolDTO.getCourse_id()).orElse(null);
        User user = userRepo.findById(stuEnrolDTO.getStu_id()).orElse(null);

        studentEnrolledCourses.setCourse(course);
        studentEnrolledCourses.setUser(user);
        studentEnrolledCourses.setCreatedBy(studentEnrolledCourses.getCreatedBy());
        studentEnrolledCourses.setCreatedDate(studentEnrolledCourses.getCreatedDate());
        studentEnrolledCourses.setLastModifiedBy(studentEnrolledCourses.getLastModifiedBy());
        studentEnrolledCourses.setLastModifiedDate(studentEnrolledCourses.getLastModifiedDate());
        return studentEnrollCourRepo.save(studentEnrolledCourses);
    }

    @Override
    public StudentEnrolledCourses findById(Long id) {
        return studentEnrollCourRepo.findById(id).orElse(null);
    }

    @Override
    public List<StudentEnrolledCourses> findAll() {
        return studentEnrollCourRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        studentEnrollCourRepo.deleteById(id);

    }
}
