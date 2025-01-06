package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.LessonRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.LessonDetailsResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.LessonResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonResponseDto toDto(Lesson lesson);
    LessonDetailsResponseDto toDetailsDto(Lesson lesson);
    Lesson toEntity(LessonRequestDto lessonRequestDto);
}
