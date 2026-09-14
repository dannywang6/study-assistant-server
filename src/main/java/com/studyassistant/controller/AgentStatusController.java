package com.studyassistant.controller;

import com.studyassistant.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/agent")
public class AgentStatusController {

    @PostMapping("/status")
    public ApiResponse<Void> receiveStatus(@RequestBody Map<String, Object> body) {
        System.out.println("Heartbeat: " + body);
        return ApiResponse.success(null);
    }
}