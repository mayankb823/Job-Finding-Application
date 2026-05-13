package com.project.notificationms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendJobApplyEmail(String toEmail, String jobTitle) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject("Job Application Confirmation");

        message.setText("You successfully applied for the job: " + jobTitle);

        mailSender.send(message);

        System.out.println("Email sent to: " + toEmail);
    }
}