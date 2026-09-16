package com.studyassistant.service.impl;


import com.studyassistant.dto.CurrentStatusRequestDTO;
import com.studyassistant.dto.DashboardStatusDTO;
import com.studyassistant.model.Category;
import com.studyassistant.service.CategoryService;
import com.studyassistant.service.CurrentStatusService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CurrentStatusServiceImpl implements CurrentStatusService {

    private final ConcurrentHashMap<String, CurrentStatusRequestDTO> map = new ConcurrentHashMap<>();
    private final CategoryService categoryService;

    public CurrentStatusServiceImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void update(CurrentStatusRequestDTO status) {
        map.put(status.getAgentId(), status);
    }

    @Override
    public DashboardStatusDTO getStatus(String agentId) {
        CurrentStatusRequestDTO status = map.get(agentId);
        return status == null ? null : toDashboard(status);
    }

    @Override
    public List<DashboardStatusDTO> getDashboard() {
        return map.values().stream().map(this::toDashboard).toList();
    }

    private DashboardStatusDTO toDashboard(CurrentStatusRequestDTO s) {
        LocalDateTime now = LocalDateTime.now();

        int elapsed = 0;
        if (s.getActivityStartTime() != null) {
            elapsed = (int) Duration.between(s.getActivityStartTime(), now).toMinutes();
        }

        boolean online = false;
        if (s.getObservedAt() != null) {
            online = Duration.between(s.getObservedAt(), now).getSeconds() < 60;
        }

        String cat = null;
        String sub = null;
        if (categoryService != null) {
            Category c = categoryService.classify(s.getApp(), s.getTitle(), s.getUrl());
            if (c != null) {
                cat = c.getCategory();
                sub = c.getSubcategory();
            }
        }

        return new DashboardStatusDTO(
                s.getAgentId(), s.getApp(), s.getTitle(), s.getUrl(),
                cat, sub, s.getActivityStartTime(), elapsed,
                s.isIdle(), online, s.getObservedAt()
        );
    }
}