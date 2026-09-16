package com.studyassistant.service;


import com.studyassistant.dto.CurrentStatusRequestDTO;
import com.studyassistant.dto.DashboardStatusDTO;

import java.util.List;

public interface CurrentStatusService {

    void update(CurrentStatusRequestDTO status);

    DashboardStatusDTO getStatus(String agentId);

    List<DashboardStatusDTO> getDashboard();

}