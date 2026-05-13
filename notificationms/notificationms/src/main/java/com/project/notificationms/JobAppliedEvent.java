package com.project.notificationms;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobAppliedEvent {

    private Long userId;

    private Long jobId;

    private String email;

    private String jobTitle;
}