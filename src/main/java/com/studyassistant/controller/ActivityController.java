package com.studyassistant.controller;


import com.studyassistant.common.ApiResponse;
import com.studyassistant.dto.ActivityPageResponseDTO;
import com.studyassistant.dto.ActivityQueryDTO;
import com.studyassistant.dto.ActivityReportRequestDTO;
import com.studyassistant.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping("/report")
    public ApiResponse<Void> report(@RequestBody List<ActivityReportRequestDTO> request) {
        activityService.saveActivities(request);
        return ApiResponse.success(null);
    }

    @GetMapping("/records")
    public ApiResponse<ActivityPageResponseDTO> records(ActivityQueryDTO dto) {
        return ApiResponse.success(activityService.queryPage(dto));
    }
}
