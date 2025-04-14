package com.example.agri.service;

import com.example.agri.Entity.TraineeCourses;

import java.util.List;

public interface TraineCourService {
    TraineeCourses save(TraineeCourses traineeCourse);
    TraineeCourses findById(Long id);
    List<TraineeCourses> findAll();
    void deleteById(int id);
}
