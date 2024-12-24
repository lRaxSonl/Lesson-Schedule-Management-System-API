package com.scheduleManagementSystem.scheduleManagementSystem.services;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.ScheduleRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.ScheduleResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.ClassRoomRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.LessonRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.ScheduleMapper;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.ScheduleRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.models.ClassRoom;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Lesson;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Schedule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final LessonRepository lessonRepository;
    private final ClassRoomRepository classRoomRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleResponseDto getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        Lesson lesson = lessonRepository.findById(schedule.getLesson().getId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        ClassRoom classRoom = classRoomRepository.findById(schedule.getClassRoom().getId())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        return scheduleMapper.toDto(schedule, lesson, classRoom);
    }

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        Schedule schedule = scheduleMapper.toEntity(dto);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return getScheduleById(savedSchedule.getId());
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        schedule.setLesson(lessonRepository.findById(dto.getLessonId()).orElseThrow(() ->
                new RuntimeException("Lesson not found.")
        ));

        schedule.setClassRoom(classRoomRepository.findById(dto.getClassRoomId()).orElseThrow(() ->
                new RuntimeException("Classroom not found.")
        ));

        schedule.setStartTime(LocalDateTime.parse(dto.getStartTime()));
        schedule.setEndTime(LocalDateTime.parse(dto.getEndTime()));

        Schedule updatedSchedule = scheduleRepository.save(schedule);
        return getScheduleById(updatedSchedule.getId());
    }

    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        scheduleRepository.delete(schedule);
    }
}
