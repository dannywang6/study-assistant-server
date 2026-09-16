package com.studyassistant.controller;


import com.studyassistant.common.ApiResponse;
import com.studyassistant.dto.DashboardStatusDTO;
import com.studyassistant.service.CurrentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/agent")
public class DashboardController {

    @Autowired
    private CurrentStatusService currentStatusService;

    @GetMapping("/status/{agentId}")
    public ApiResponse<DashboardStatusDTO> getStatus(@PathVariable String agentId) {
        DashboardStatusDTO status = currentStatusService.getStatus(agentId);
        if (status == null) {
            return ApiResponse.error(404, "Agent not found");
        }
        return ApiResponse.success(status);
    }

    @GetMapping("/dashboard/current")
    public ApiResponse<List<DashboardStatusDTO>> dashboard() {
        return ApiResponse.success(currentStatusService.getDashboard());
    }
}