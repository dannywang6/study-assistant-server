package com.studyassistant.service.impl;

import com.studyassistant.converter.ActivityConverter;
import com.studyassistant.dto.ActivityReportRequestDTO;
import com.studyassistant.dto.IdleReportRequestDTO;
import com.studyassistant.entity.ActivityEntity;
import com.studyassistant.entity.IdleEntity;
import com.studyassistant.mapper.ActivityMapper;
import com.studyassistant.mapper.IdleMapper;
import com.studyassistant.model.Category;
import com.studyassistant.service.ActivityService;
import com.studyassistant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private IdleMapper idleMapper;

    @Autowired
    private ActivityConverter converter;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public void saveActivities(List<ActivityReportRequestDTO> requests) {
        for (ActivityReportRequestDTO req : requests) {
            ActivityEntity activity = converter.toActivity(req);

            Category category = categoryService.classify(req.getApp(), req.getTitle(), req.getUrl());
            if (category != null) {
                activity.setCategory(category.getCategory());
                activity.setSubcategory(category.getSubcategory());
            }

            activityMapper.insert(activity);

            if (req.getIdleRecords() != null) {
                for (IdleReportRequestDTO idleReq : req.getIdleRecords()) {
                    IdleEntity idle = converter.toIdle(idleReq);
                    idle.setActivityId(activity.getId());
                    idleMapper.insert(idle);
                }
            }
        }
    }

}
