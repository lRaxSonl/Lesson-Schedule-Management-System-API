package com.scheduleManagementSystem.scheduleManagementSystem.controllers;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.StudentRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.StudentResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto createStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return studentService.createStudent(studentRequestDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDto updateStudent(@PathVariable Long id, @RequestBody StudentRequestDto studentRequestDto) {
        return studentService.updateStudent(id, studentRequestDto);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

}
