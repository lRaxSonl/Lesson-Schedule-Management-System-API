package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.SubjectRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.SubjectResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Subject;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectResponseDto toDto(Subject subject);
    Subject toEntity(SubjectRequestDto subjectRequestDto);
}
