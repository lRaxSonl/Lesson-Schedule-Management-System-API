package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.StudentRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.StudentResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentResponseDto toDto(Student student);
    Student toEntity(StudentRequestDto studentRequestDto);

}
