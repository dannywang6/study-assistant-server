package com.studyassistant.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class IdleReportRequestDTO {

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}