package com.studyassistant.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IdleEntity {
    private Long id;
    private Long activityId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
