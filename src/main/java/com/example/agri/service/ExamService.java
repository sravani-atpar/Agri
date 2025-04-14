package com.example.agri.service;

import com.example.agri.DTO.ExamTotalDTO;
import com.example.agri.DTO.HighestMarksBySubjectDTO;
import com.example.agri.Entity.Exam;
import com.example.agri.Repository.ExamRepo;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface ExamService {
    Exam save(Exam exam);
    Exam findById(Long id);
    List<Exam> findAll();
    void deleteById(Long id);
    List<ExamTotalDTO> findExamByStudent_IdAndExamName(long student_id, Exam.ExamType examName);
    List<HighestMarksBySubjectDTO> getHighestScoringStudents(String examName, String subject);

    String exportExamsToCSV(String filePath) throws IOException;




}




























