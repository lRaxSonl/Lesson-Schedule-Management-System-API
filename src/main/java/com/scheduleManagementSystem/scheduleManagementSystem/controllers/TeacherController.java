package com.scheduleManagementSystem.scheduleManagementSystem.controllers;


import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.TeacherResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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
}
