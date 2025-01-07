package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.LessonRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.LessonDetailsResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.LessonResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subject.id", target = "subjectId")
    LessonResponseDto toDto(Lesson lesson);

    @Mapping(source = "teacher.fullname", target = "teacherName")
    @Mapping(source = "subject.name", target = "subjectName")
    @Mapping(source = "group.name", target = "groupName")
    LessonDetailsResponseDto toDetailsDto(Lesson lesson);
    Lesson toEntity(LessonRequestDto lessonRequestDto);
}
