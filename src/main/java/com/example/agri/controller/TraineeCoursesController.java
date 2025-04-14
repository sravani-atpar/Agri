package com.example.agri.controller;

import com.example.agri.Entity.TraineeCourses;
import com.example.agri.Repository.TraineeCourRepo;
import com.example.agri.service.TraineCourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainee-courses")
public class TraineeCoursesController {
    @Autowired
    private TraineCourService traineeCourService;

    @Autowired
    private TraineeCourRepo traineeCourRepo;

    public TraineeCoursesController(TraineeCourRepo traineeCourRepo) {
        this.traineeCourRepo = traineeCourRepo;
    }

    @PostMapping
    public ResponseEntity<TraineeCourses> saveTraineeCourse(@RequestBody TraineeCourses traineeCourses) {
        TraineeCourses savedCourse = traineeCourRepo.save(traineeCourses);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TraineeCourses>> getTraineeCourses() {
        List<TraineeCourses> courses = traineeCourRepo.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TraineeCourses> getTraineeCourseById(@PathVariable Long id) {
        Optional<TraineeCourses> traineeCourse = traineeCourRepo.findById(id);
        if (traineeCourse.isPresent()) {
            return new ResponseEntity<>(traineeCourse.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TraineeCourses> updateTraineeCourse(
            @PathVariable Long id,
            @RequestBody TraineeCourses updatedTraineeCourse) {

        Optional<TraineeCourses> existingCourseOptional = traineeCourRepo.findById(id);

        if (existingCourseOptional.isPresent()) {
            TraineeCourses existingCourse = existingCourseOptional.get();

            TraineeCourses savedCourse = traineeCourRepo.save(existingCourse);

            return new ResponseEntity<>(savedCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraineeCourse(@PathVariable Long id) {
        Optional<TraineeCourses> traineeCourse = traineeCourRepo.findById(id);

        if (traineeCourse.isPresent()) {
            traineeCourRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
