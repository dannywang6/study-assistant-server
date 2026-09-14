package com.studyassistant.controller;


import com.studyassistant.common.ApiResponse;
import com.studyassistant.dto.ActivityReportRequestDTO;
import com.studyassistant.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
