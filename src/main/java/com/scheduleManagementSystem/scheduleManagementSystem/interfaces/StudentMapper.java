package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.StudentRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.StudentResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Group;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "student.group.name", target = "groupName")
    StudentResponseDto toDto(Student student);


    @Mapping(source = "studentRequestDto.group", target = "group")
    Student toEntity(StudentRequestDto studentRequestDto);

    default Group map(Long value) {
        if (value == null) {
            return null;
        }
        Group group = new Group();
        group.setId(value);
        return group;
    }
}
