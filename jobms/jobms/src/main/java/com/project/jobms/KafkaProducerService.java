package com.project.jobms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, JobAppliedEvent> kafkaTemplate;

    private static final String TOPIC =
            "job-applied-topic";

    public void sendEvent(JobAppliedEvent event){

        kafkaTemplate.send(TOPIC, event);

        System.out.println(
                "Event Published"
        );
    }
}