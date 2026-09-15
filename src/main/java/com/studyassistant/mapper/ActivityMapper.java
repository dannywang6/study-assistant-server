package com.studyassistant.mapper;


import com.studyassistant.dto.ActivityQueryDTO;
import com.studyassistant.entity.ActivityEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {

    void insert(ActivityEntity activityEntity);

    List<ActivityEntity> selectPage(ActivityQueryDTO dto);

    long count(ActivityQueryDTO dto);
}
