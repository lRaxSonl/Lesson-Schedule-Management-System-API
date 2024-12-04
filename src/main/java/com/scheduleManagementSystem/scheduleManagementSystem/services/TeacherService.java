package com.scheduleManagementSystem.scheduleManagementSystem.services;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.TeacherResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.TeacherMapper;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.TeacherRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherResponseDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Teacher not found"));

        TeacherResponseDto dto = teacherMapper.toDto(teacher);
        return dto;
    }
}
