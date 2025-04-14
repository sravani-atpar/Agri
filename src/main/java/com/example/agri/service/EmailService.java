package com.example.agri.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithCSV(String toEmail, String subject, String body, String csvFilePath) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        // true => multipart (to allow attachments)
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText("<h3>" + body + "</h3>", true);  // HTML body

        // Attach CSV File
        FileSystemResource file = new FileSystemResource(new File(csvFilePath));
        if (file.exists()) {
            helper.addAttachment(file.getFilename(), file);
        } else {
            System.out.println("CSV file not found at: " + csvFilePath);
        }

        javaMailSender.send(message);
        System.out.println("Email sent successfully with attachment.");
    }
}
