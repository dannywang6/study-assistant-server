package com.studyassistant.converter;


import com.studyassistant.dto.ActivityReportRequestDTO;
import com.studyassistant.dto.IdleReportRequestDTO;
import com.studyassistant.entity.ActivityEntity;
import com.studyassistant.entity.IdleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityConverter {

    ActivityEntity toActivity(ActivityReportRequestDTO request);

    IdleEntity toIdle(IdleReportRequestDTO request);
}
