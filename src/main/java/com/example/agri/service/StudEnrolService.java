package com.example.agri.service;

import com.example.agri.DTO.StuEnrolDTO;
import com.example.agri.Entity.Course;
import com.example.agri.Entity.StudentEnrolledCourses;

import java.util.List;

public interface StudEnrolService {
  StudentEnrolledCourses save(StuEnrolDTO stuEnrolDTO) ;
  StudentEnrolledCourses findById(Long  id);
  List<StudentEnrolledCourses> findAll();
  void deleteById(Long id);




}
