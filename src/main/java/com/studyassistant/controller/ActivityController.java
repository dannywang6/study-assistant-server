package com.studyassistant.controller;


import com.studyassistant.common.ApiResponse;
import com.studyassistant.dto.ActivityPageResponseDTO;
import com.studyassistant.dto.ActivityQueryDTO;
import com.studyassistant.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/records")
    public ApiResponse<ActivityPageResponseDTO> records(ActivityQueryDTO dto) {
        return ApiResponse.success(activityService.queryPage(dto));
    }
}