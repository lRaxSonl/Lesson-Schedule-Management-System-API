package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.TeacherRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.TeacherResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherResponseDto toDto(Teacher teacher);
    Teacher toEntity(TeacherRequestDto teacherRequestDto);
}
