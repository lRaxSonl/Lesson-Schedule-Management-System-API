package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.GroupRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.GroupResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupResponseDto toDto(Group group);
    Group toEntity(GroupRequestDto groupRequestDto);
}
