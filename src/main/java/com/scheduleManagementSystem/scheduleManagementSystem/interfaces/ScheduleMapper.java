package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.ScheduleRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.ScheduleResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(source = "schedule.lesson.id", target = "lessonId")
    @Mapping(source = "schedule.lesson.teacher.fullname", target = "teacherName")
    @Mapping(source = "schedule.lesson.subject.name", target = "subjectName")
    @Mapping(source = "schedule.lesson.group.name", target = "groupName")
    @Mapping(source = "schedule.classRoom.id", target = "classRoomId")
    @Mapping(source = "schedule.classRoom.name", target = "classRoomName")
    @Mapping(source = "schedule.classRoom.location", target = "classRoomLocation")
    ScheduleResponseDto toDto(Schedule schedule);
    Schedule toEntity(ScheduleRequestDto scheduleRequestDto);
}
