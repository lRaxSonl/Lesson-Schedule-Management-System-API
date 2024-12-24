package com.scheduleManagementSystem.scheduleManagementSystem.dto.response;

import lombok.Data;


@Data
public class ScheduleResponseDto {
    private Long id;
    private Long lessonId;
    private String teacherName;
    private String subjectName;
    private String groupName;
    private Long classRoomId;
    private String classRoomName;
    private String classRoomLocation;
    private String startTime;
    private String endTime;
}
