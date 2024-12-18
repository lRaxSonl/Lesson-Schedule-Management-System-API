package com.scheduleManagementSystem.scheduleManagementSystem.dto.response;

import lombok.Data;

@Data
public class LessonResponseDto {
    private Long id;
    private Long teacherId;
    private Long subjectId;
    private Long groupId;
}
