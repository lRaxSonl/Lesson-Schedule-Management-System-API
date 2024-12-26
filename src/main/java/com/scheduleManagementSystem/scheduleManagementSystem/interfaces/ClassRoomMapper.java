package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.ClassRoomRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.ClassRoomResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.ClassRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassRoomMapper {
    ClassRoomResponseDto toDto(ClassRoom classRoom);
    ClassRoom toEntity(ClassRoomRequestDto classRoomRequestDto);
}
