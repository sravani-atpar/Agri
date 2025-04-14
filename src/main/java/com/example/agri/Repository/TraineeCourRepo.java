package com.example.agri.Repository;

import com.example.agri.Entity.TraineeCourses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraineeCourRepo extends JpaRepository<TraineeCourses,Long> {
    List<TraineeCourses> id(int id);
}
