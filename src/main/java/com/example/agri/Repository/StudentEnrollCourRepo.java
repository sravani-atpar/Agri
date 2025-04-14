package com.example.agri.Repository;

import com.example.agri.Entity.Course;
import com.example.agri.Entity.StudentEnrolledCourses;
import com.example.agri.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEnrollCourRepo extends JpaRepository <StudentEnrolledCourses, Long>{
}

