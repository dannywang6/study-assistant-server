package com.studyassistant.mapper;


import com.studyassistant.entity.ActivityEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityMapper {

    void insert(ActivityEntity activityEntity);
}
