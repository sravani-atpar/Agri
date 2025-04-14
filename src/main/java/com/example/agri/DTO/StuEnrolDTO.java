package com.example.agri.DTO;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

public class StuEnrolDTO {
    private Long stu_id;
    private Long course_id;
    private String amount;



    public String getAmount() {
        return amount;
    }

    public Long getStu_id() {
        return stu_id;
    }

    public void setStu_id(Long stu_id) {
        this.stu_id = stu_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}