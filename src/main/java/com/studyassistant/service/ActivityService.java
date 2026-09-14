package com.studyassistant.service;


import com.studyassistant.dto.ActivityReportRequestDTO;

import java.util.List;

public interface ActivityService {

    void saveActivities(List<ActivityReportRequestDTO> requests);
}
