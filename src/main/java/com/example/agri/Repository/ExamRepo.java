package com.example.agri.Repository;


import com.example.agri.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Long> {
        @Query(value = "SELECT * FROM exam e WHERE e.student_id = :student_id AND e.exam_name = :exam_name", nativeQuery = true)
    List<Exam> findExamByStudent_IdAndExamName(@Param("student_id") long studentId,
                                               @Param("exam_name") String examName);

    @Query(value = """
        SELECT s.student_name, e.exam_name, :subject, e.marks
        FROM student s
        JOIN (
            SELECT student_id, exam_name, 
                   CASE 
                       WHEN :subject = 'telugu' THEN telugu
                       WHEN :subject = 'hindi' THEN hindi
                       WHEN :subject = 'english' THEN english
                       WHEN :subject = 'maths' THEN maths
                       WHEN :subject = 'science' THEN science
                       WHEN :subject = 'social' THEN social
                   END AS marks
            FROM exam
            WHERE exam_name = :examName
        ) e ON e.student_id = s.id
        WHERE e.marks = (
            SELECT MAX(CASE 
                           WHEN :subject = 'telugu' THEN telugu
                           WHEN :subject = 'hindi' THEN hindi
                           WHEN :subject = 'english' THEN english
                           WHEN :subject = 'maths' THEN maths
                           WHEN :subject = 'science' THEN science
                           WHEN :subject = 'social' THEN social
                       END)
            FROM exam
            WHERE exam_name = :examName
        )
        """, nativeQuery = true)
    List<Object[]> findStudentsWithHighestMarks(@Param("examName") String examName,
                                                @Param("subject") String subject);
}

