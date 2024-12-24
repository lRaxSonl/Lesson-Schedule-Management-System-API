package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.ScheduleRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.ScheduleResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.ClassRoom;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Lesson;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(source = "lesson.id", target = "lessonId")
    @Mapping(source = "lesson.teacherName", target = "teacherName")
    @Mapping(source = "lesson.subjectName", target = "subjectName")
    @Mapping(source = "lesson.groupName", target = "groupName")
    @Mapping(source = "classRoom.id", target = "classRoomId")
    @Mapping(source = "classRoom.name", target = "classRoomName")
    @Mapping(source = "classRoom.location", target = "classRoomLocation")
    ScheduleResponseDto toDto(Schedule schedule, Lesson lesson, ClassRoom classRoom);
    Schedule toEntity(ScheduleRequestDto scheduleRequestDto);
}
