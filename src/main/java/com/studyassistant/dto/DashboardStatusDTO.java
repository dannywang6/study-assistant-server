package com.studyassistant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DashboardStatusDTO {

    private String agentId;
    private String app;
    private String title;
    private String url;
    private String category;
    private String subcategory;
    private LocalDateTime activityStartTime;
    private int elapsedMinutes;
    private boolean idle;
    private boolean online;
    private LocalDateTime lastSeen;
}