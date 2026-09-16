package com.studyassistant.controller;


import com.studyassistant.common.ApiResponse;
import com.studyassistant.dto.ActivityReportRequestDTO;
import com.studyassistant.dto.CurrentStatusRequestDTO;
import com.studyassistant.service.ActivityService;
import com.studyassistant.service.CurrentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AgentUploadController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CurrentStatusService currentStatusService;

    @PostMapping("/agent/status")
    public ApiResponse<Void> receiveStatus(@RequestBody CurrentStatusRequestDTO status) {
        currentStatusService.update(status);
        return ApiResponse.success(null);
    }

    @PostMapping("/activity/report")
    public ApiResponse<Void> report(@RequestBody List<ActivityReportRequestDTO> request) {
        activityService.saveActivities(request);
        return ApiResponse.success(null);
    }
}