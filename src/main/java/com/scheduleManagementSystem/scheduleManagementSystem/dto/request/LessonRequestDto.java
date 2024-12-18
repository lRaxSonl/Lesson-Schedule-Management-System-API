package com.scheduleManagementSystem.scheduleManagementSystem.dto.request;

import lombok.Data;

@Data
public class LessonRequestDto {
    private Long teacherId;
    private Long subjectId;
    private Long groupId;
}
