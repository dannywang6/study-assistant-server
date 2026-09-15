package com.studyassistant.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ActivityQueryDTO {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;

    private String category;

    private String subcategory;

    private String app;

    private Integer page = 1;

    private Integer size = 20;

    public int getOffset() {
        return (page - 1) * size;
    }
}
