package com.example.agri.controller;

import com.example.agri.DTO.ExamTotalDTO;
import com.example.agri.DTO.HighestMarksBySubjectDTO;
import com.example.agri.Entity.Exam;
import com.example.agri.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ExamController {
    @Autowired
    ExamService examService;

    @PostMapping("/api/exam")
    public ResponseEntity<Exam> saveExam(@RequestBody Exam exam) {
        return new ResponseEntity<>(examService.save(exam), HttpStatus.CREATED);
        

    }

    @GetMapping("/api/exam")
    public ResponseEntity<List<Exam>> getAllExams() {
        return new ResponseEntity<>(examService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/exam/{id}")
    public ResponseEntity<Exam> getExams(@PathVariable long id) {
        Exam exam = examService.findById(id);
//        if (exam.isPresent()) {
//            return new ResponseEntity<>(exam.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(exam, HttpStatus.OK);

    }
    @GetMapping("/api/exam/{Student_id}/{exam_name}")
    public ResponseEntity<List<ExamTotalDTO>> findExamByStudent_IdAndExamName(@PathVariable long Student_id, @PathVariable String exam_name ) {
        List<ExamTotalDTO> exams= examService.findExamByStudent_IdAndExamName(Student_id , Exam.ExamType.valueOf(exam_name));
        return new ResponseEntity<>(exams, HttpStatus.OK);

    }
    @GetMapping("/{examName}/highest-marks")
    public ResponseEntity<List<HighestMarksBySubjectDTO>> getHighestScoringStudents(
            @PathVariable String examName,
            @RequestParam String subject) {
        List<HighestMarksBySubjectDTO> students = examService.getHighestScoringStudents(examName, subject);
        return ResponseEntity.ok(students);
    }
    @PutMapping("/api/exam/{id}")
    public ResponseEntity<Exam> updateExams(@PathVariable long id,@RequestBody Exam exams) {
        Exam exam = examService.findById(id);
        if (exam!=null) {
            exam.setExamName(exams.getExamName());
            exam.setEnglish(exams.getEnglish());
            exam.setHindi(exams.getHindi());
            exam.setMaths(exams.getMaths());
            exam.setScience(exams.getScience());
            exam.setSocial(exams.getSocial());
            exam.setTelugu(exams.getTelugu());
            return new ResponseEntity<>(examService.save(exam), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/exam/{id}")
    public ResponseEntity<Exam> deleteExams(@PathVariable long id) {
        Exam exam = examService.findById(id);
        if (exam!=null) {
            examService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//    package com.example.agri.controller;
//
//import com.example.agri.service.ExamCSVService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;



    @RestController
    @RequestMapping("/exams")
    public class examController {

        private final ExamService examService;

        public examController(ExamService examService) {
            this.examService = examService;
        }

        @GetMapping("/export-csv")
        public String exportCSV() throws IOException {
            String filePath = "E:\\AGRI\\agri\\exam.csv";
            String result = examService.exportExamsToCSV(filePath);

            if (result != null) {
                return "✅ CSV file generated at: " + filePath;
            } else {
                return "❌ Failed to generate CSV file.";
 }
}
    }

}
