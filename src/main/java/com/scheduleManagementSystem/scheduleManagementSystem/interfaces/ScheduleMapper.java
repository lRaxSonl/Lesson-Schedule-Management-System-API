package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.ScheduleRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.ScheduleResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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


    @Named("parseDateTime")
    default LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    @Mapping(source = "scheduleRequestDto.classRoomId", target = "classRoom.id")
    @Mapping(source = "scheduleRequestDto.lessonId", target = "lesson.id")
    @Mapping(source = "scheduleRequestDto.startTime", target = "startTime", qualifiedByName = "parseDateTime")
    @Mapping(source = "scheduleRequestDto.endTime", target = "endTime", qualifiedByName = "parseDateTime")
    Schedule toEntity(ScheduleRequestDto scheduleRequestDto);
}
