package com.studyassistant.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ActivityReportRequestDTO {

    private String app;

    private String title;

    private String url;

    private String category;

    private String subcategory;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private List<IdleReportRequestDTO> idleRecords;
}