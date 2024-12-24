package com.scheduleManagementSystem.scheduleManagementSystem.dto.request;

import lombok.Data;


@Data
public class ScheduleRequestDto {
    private Long lessonId;
    private Long classRoomId;
    private String startTime;
    private String endTime;
}
