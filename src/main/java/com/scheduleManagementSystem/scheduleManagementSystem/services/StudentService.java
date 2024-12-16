package com.scheduleManagementSystem.scheduleManagementSystem.services;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.StudentResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.StudentMapper;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.StudentRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Student not found"));

        StudentResponseDto dto = studentMapper.toDto(student);
        return dto;
    }
}
