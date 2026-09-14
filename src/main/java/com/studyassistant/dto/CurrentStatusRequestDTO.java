package com.studyassistant.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CurrentStatusRequestDTO {
    private String agentId;
    private String app;
    private String title;
    private String url;
    private String activityEventId;
    private LocalDateTime activityStartTime;
    private LocalDateTime observedAt;
    private boolean idle;
    private LocalDateTime idleStartTime;
}