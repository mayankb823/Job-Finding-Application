package com.project.jobms;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobAppliedEvent {

    private Long userId;

    private String email;

    private Long jobId;

    private String jobTitle;
}