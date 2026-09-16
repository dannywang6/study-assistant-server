package com.studyassistant.service.impl;

import com.studyassistant.converter.ActivityConverter;
import com.studyassistant.dto.ActivityPageResponseDTO;
import com.studyassistant.dto.ActivityQueryDTO;
import com.studyassistant.dto.ActivityReportRequestDTO;
import com.studyassistant.dto.IdleReportRequestDTO;
import com.studyassistant.entity.ActivityEntity;
import com.studyassistant.entity.IdleEntity;
import com.studyassistant.mapper.ActivityMapper;
import com.studyassistant.mapper.IdleMapper;
import com.studyassistant.model.Category;
import com.studyassistant.service.ActivityService;
import com.studyassistant.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityMapper activityMapper;
    private final IdleMapper idleMapper;
    private final ActivityConverter converter;
    private final CategoryService categoryService;

    public ActivityServiceImpl(ActivityMapper activityMapper,
                               IdleMapper idleMapper,
                               ActivityConverter converter,
                               CategoryService categoryService) {
        this.activityMapper = activityMapper;
        this.idleMapper = idleMapper;
        this.converter = converter;
        this.categoryService = categoryService;
    }

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

    @Override
    public ActivityPageResponseDTO queryPage(ActivityQueryDTO dto) {
        int page = dto.getPage() < 1 ? 1 : dto.getPage();
        int size = dto.getSize() < 1 ? 20 : dto.getSize();
        if (size > 200) size = 200;
        dto.setPage(page);
        dto.setSize(size);

        long total = activityMapper.count(dto);
        List<ActivityEntity> list = activityMapper.selectPage(dto);
        return new ActivityPageResponseDTO(total, page, size, list);
    }

}
