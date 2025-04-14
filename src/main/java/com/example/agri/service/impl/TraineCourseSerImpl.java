package com.example.agri.service.impl;

import com.example.agri.Entity.TraineeCourses;
import com.example.agri.Repository.TraineeCourRepo;
import com.example.agri.service.TraineCourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class TraineCourseSerImpl implements TraineCourService {

    @Autowired
    private TraineeCourRepo traineeCourRepo; // Fixed: Properly declared and injected

    @Override
    public TraineeCourses save(TraineeCourses traineeCourse) {
        traineeCourse.setCreatedBy("traineeCourse");
        traineeCourse.setLastModifiedBy("traineeCourse");
        traineeCourse.setCreatedDate(Timestamp.from(Instant.now()));
        traineeCourse.setLastModifiedDate(Timestamp.from(Instant.now()));

        return traineeCourRepo.save(traineeCourse); // Fixed: Correct reference to traineeCourRepo
    }

    @Override
    public TraineeCourses findById(Long id) {
        return traineeCourRepo.findById(id).orElse(null); // Fixed: Correct reference
    }

    @Override
    public List<TraineeCourses> findAll() {
        return traineeCourRepo.findAll(); // Fixed: Correct reference
    }

    @Override
    public void deleteById(int id) {
        traineeCourRepo.deleteById((long) id); // Fixed: Proper method call
    }
}
