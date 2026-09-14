package com.studyassistant.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityEntity {
    private Long id;
    private String app;
    private String title;
    private String url;
    private String category;
    private String subcategory;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
