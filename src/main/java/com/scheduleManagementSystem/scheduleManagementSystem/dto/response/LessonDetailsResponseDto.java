package com.scheduleManagementSystem.scheduleManagementSystem.dto.response;
import lombok.Data;


@Data
public class LessonDetailsResponseDto {
    private Long id;
    private String teacherName;
    private String subjectName;
    private String groupName;
}
