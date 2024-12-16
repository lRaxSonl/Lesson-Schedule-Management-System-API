package com.scheduleManagementSystem.scheduleManagementSystem.controllers;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.StudentResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDto getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

}
