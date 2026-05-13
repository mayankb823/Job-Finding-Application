package com.project.notificationms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "job-applied-topic", groupId = "notification-group")
    public void consume(JobAppliedEvent event) {

        emailService.sendJobApplyEmail(
                event.getEmail(),
                event.getJobTitle()
        );

        System.out.println(
                "Email sent to: "
                        + event.getEmail()
        );
    }
}