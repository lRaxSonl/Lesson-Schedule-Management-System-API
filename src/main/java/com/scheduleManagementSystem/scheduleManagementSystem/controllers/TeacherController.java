package com.scheduleManagementSystem.scheduleManagementSystem.controllers;


import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.TeacherRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.TeacherResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherResponseDto getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherResponseDto> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponseDto createTeacher(@RequestBody TeacherRequestDto teacherRequestDto) {
        return teacherService.createTeacher(teacherRequestDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherResponseDto updateTeacher(@PathVariable Long id, @RequestBody TeacherRequestDto teacherRequestDto) {
        return teacherService.updateTeacher(id, teacherRequestDto);
    }

    // Delete a teacher
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

}
