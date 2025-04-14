package com.example.agri.CronJob;

import com.example.agri.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronJobRun {
    @Autowired
    private EmailService emailService;

    // Runs every day at 3:00 PM
    @Scheduled(cron = "0 20 15 * * *")
    public void runTask() throws MessagingException {
        System.out.println("Cron job executed at 3:00 PM: " + java.time.LocalDateTime.now());

        // Email details
        String toEmail = "bandisravani03@gmail.com";
        String subject = "Daily Exam Report - CSV Attached";
        String body = "Hello! Please find the attached exam report CSV file.";

        // CSV File Path (this file must be generated before sending)
        String csvFilePath = "E:/AGRI/agri/exam.csv";

        // Send email with attachment
        emailService.sendEmailWithCSV(toEmail, subject, body, csvFilePath);
    }
}
