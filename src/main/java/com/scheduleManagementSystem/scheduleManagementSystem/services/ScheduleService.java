package com.scheduleManagementSystem.scheduleManagementSystem.services;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.ScheduleRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.ScheduleResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.*;
import com.scheduleManagementSystem.scheduleManagementSystem.models.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final LessonRepository lessonRepository;
    private final ClassRoomRepository classRoomRepository;
    private final ScheduleMapper scheduleMapper;
    private final NotificationService notificationService;
    private final StudentRepository studentRepository;

    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAll().stream().map(schedule -> {
            Lesson lesson = lessonRepository.findById(schedule.getLesson().getId())
                    .orElseThrow(() -> new RuntimeException("Lesson not found."));
            ClassRoom classRoom = classRoomRepository.findById(schedule.getClassRoom().getId())
                    .orElseThrow(() -> new RuntimeException("Classroom not found."));
            return scheduleMapper.toDto(schedule);
        }).collect(Collectors.toList());
    }

    public ScheduleResponseDto getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        Lesson lesson = lessonRepository.findById(schedule.getLesson().getId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        ClassRoom classRoom = classRoomRepository.findById(schedule.getClassRoom().getId())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        return scheduleMapper.toDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        try {
            Schedule schedule = scheduleMapper.toEntity(dto);
            Schedule savedSchedule = scheduleRepository.save(schedule);
            return getScheduleById(savedSchedule.getId());
        } catch (Exception e) {
            throw new RuntimeException("Error creating schedule: " + e.getMessage());
        }
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


        // Отправка уведомлений студентам группы
        Lesson lesson = updatedSchedule.getLesson();
        Group group = lesson.getGroup();
        String message = "Расписание для группы " + group.getName() + " было обновлено.";

        notificationService.createNotification(
                message,
                studentRepository.findByGroup(group).stream().map(Student::getId).collect(Collectors.toList()),
                List.of(lesson.getTeacher().getId())
        );


        return getScheduleById(updatedSchedule.getId());
    }

    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        scheduleRepository.delete(schedule);
    }
}
