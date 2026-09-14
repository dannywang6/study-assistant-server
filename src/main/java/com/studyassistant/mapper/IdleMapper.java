package com.studyassistant.mapper;


import com.studyassistant.entity.IdleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IdleMapper {

    void insert(IdleEntity idleEntity);
}
