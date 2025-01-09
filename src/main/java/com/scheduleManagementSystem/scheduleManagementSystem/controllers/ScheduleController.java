package com.scheduleManagementSystem.scheduleManagementSystem.controllers;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.ScheduleInfoRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.ScheduleRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.ScheduleResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleResponseDto getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id);
    }

    @PostMapping("/byGroup")
    @ResponseStatus(HttpStatus.OK)
    public List<ScheduleResponseDto> getScheduleByGroupName(@RequestBody ScheduleInfoRequestDto groupName) {
        return scheduleService.getSchedulesByGroupName(groupName);
    }

    @PostMapping("/byStudent")
    @ResponseStatus(HttpStatus.OK)
    public List<ScheduleResponseDto> getScheduleByStudentUsername(@RequestBody ScheduleInfoRequestDto studentUsername) {
        return scheduleService.getSchedulesByStudentUsername(studentUsername);
    }


    @PostMapping("/byTeacher")
    @ResponseStatus(HttpStatus.OK)
    public List<ScheduleResponseDto> getScheduleByTeacherName(@RequestBody ScheduleInfoRequestDto teacherUsername) {
        return scheduleService.getSchedulesByTeacherUsername(teacherUsername);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.createSchedule(scheduleRequestDto);
    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.updateSchedule(id, scheduleRequestDto);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }
}
