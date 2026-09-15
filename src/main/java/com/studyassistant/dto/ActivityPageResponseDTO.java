package com.studyassistant.dto;


import com.studyassistant.entity.ActivityEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ActivityPageResponseDTO {

    private long total;

    private int page;

    private int size;

    private List<ActivityEntity> list;
}
