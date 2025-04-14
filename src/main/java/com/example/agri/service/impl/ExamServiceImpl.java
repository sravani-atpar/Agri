package com.example.agri.service.impl;

import com.example.agri.DTO.ExamTotalDTO;
import com.example.agri.DTO.HighestMarksBySubjectDTO;
import com.example.agri.Entity.Exam;
import com.example.agri.Repository.ExamRepo;
import com.example.agri.service.ExamService;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.E;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepo examRepo;

    @Override
    public Exam save(Exam exam) {
        exam.setCreatedBy("exam");
        exam.setLastModifiedBy("exam");
        exam.setCreatedDate(Timestamp.from(Instant.now()));
        exam.setLastModifiedDate(Timestamp.from(Instant.now()));
        return examRepo.save(exam);
    }

    @Override
    public Exam findById(Long id) {
        return examRepo.findById(id).orElse(null);
    }

    @Override
    public List<Exam> findAll() {
        return examRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        examRepo.deleteById(id);

    }

    @Override
    public List<ExamTotalDTO> findExamByStudent_IdAndExamName(long student_id, Exam.ExamType examName) {
        List<Exam> exams = examRepo.findExamByStudent_IdAndExamName(student_id, String.valueOf(examName));
        List<ExamTotalDTO> examTotals = new ArrayList<>();
        for (Exam exam : exams) {
           double telugu = Double.parseDouble(exam.getTelugu());
           double hindi = Double.parseDouble(exam.getHindi());
           double english = Double.parseDouble(exam.getEnglish());
           double maths = Double.parseDouble(exam.getMaths());
           double science = Double.parseDouble(exam.getScience());
           double social = Double.parseDouble(exam.getSocial());
           double sum = telugu + hindi + english + maths + science+social;
           double percentage=((sum/600*100));
           System.out.println(sum);
           System.out.println(percentage);
            ExamTotalDTO total = new ExamTotalDTO();
            total.setId(student_id);
            total.setExamName(String.valueOf(examName));
            total.setTotalmarks(sum);
            total.setPercentage((int) percentage);
            examTotals.add(total);
        }
//        ExamTotal total = new ExamTotal();
//        total.setId(student_id);
//        total.setExamName(String.valueOf(examName));
//        total.setTotalmarks(sum);
        return examTotals;
    }

    @Override
    public List<HighestMarksBySubjectDTO> getHighestScoringStudents(String examName, String subject) {
        List<Object[]> highestMarksBySubjectDTOS = examRepo.findStudentsWithHighestMarks(examName, subject);
        System.out.println(highestMarksBySubjectDTOS);
        List<HighestMarksBySubjectDTO> highestMarksBySubjectDTOOut = new ArrayList<>();
        for(Object[] objects : highestMarksBySubjectDTOS){
            HighestMarksBySubjectDTO temp = new HighestMarksBySubjectDTO();
            System.out.println(objects[0]);
            System.out.println(objects[1]);
            System.out.println(objects[2]);
            System.out.println(objects[3]);
            temp.setStudentName(objects[0].toString());
            temp.setExamName(objects[1].toString());
            temp.setSubject(objects[2].toString());
            temp.setMarks(Integer.parseInt(objects[3].toString()));
            highestMarksBySubjectDTOOut.add(temp);
        }
//        for(HighestMarksBySubjectDTO hm : highestMarksBySubjectDTOS){
//            hm.setSubject(subject);
//        }
//        return highestMarksBySubjectDTOS;
        return highestMarksBySubjectDTOOut;
    }

    public String exportExamsToCSV(String filePath) throws IOException {
        // change path in production
        String csvpath = "E:\\AGRI\\agri\\exam.csv";
        List<Exam> exams = examRepo.findAll();

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvpath))) {
            // Header row
            String[] header = {"ID", "Exam Name", "Telugu", "Hindi", "English", "Maths", "Science", "Social"};
            writer.writeNext(header);

            // Data rows
            for (Exam exam : exams) {
                String[] data = {
                        String.valueOf(exam.getId()),
                        exam.getExamName() != null ? String.valueOf(exam.getExamName()) : "",  // Handle null
                        exam.getTelugu() != null ? exam.getTelugu() : "",
                        exam.getHindi() != null ? exam.getHindi() : "",
                        exam.getEnglish() != null ? exam.getEnglish() : "",
                        exam.getMaths() != null ? exam.getMaths() : "",
                        exam.getScience() != null ? exam.getScience() : "",
                        exam.getSocial() != null ? exam.getSocial() : ""
                };
                writer.writeNext(data);
            }
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
      }


        }
    }

