package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.GroupRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.GroupResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = StudentMapper.class)
public interface GroupMapper {

    @Mapping(source = "group.students", target = "students")
    GroupResponseDto toDto(Group group);

    Group toEntity(GroupRequestDto groupRequestDto);
}
