package com.scheduleManagementSystem.scheduleManagementSystem.services;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.TeacherRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.TeacherResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.TeacherMapper;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.TeacherRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Teacher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder passwordEncoder;

    public TeacherResponseDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Teacher not found"));

        TeacherResponseDto dto = teacherMapper.toDto(teacher);
        return dto;
    }

    public List<TeacherResponseDto> getAllTeachers() {
        return teacherRepository.findAll().stream().map(teacherMapper::toDto).collect(Collectors.toList());
    }


    @Transactional
    public TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto) {
        Teacher teacher = teacherMapper.toEntity(teacherRequestDto);

        if (teacherRepository.existsTeacherByUsername(teacher.getUsername())) {
            throw new RuntimeException("Teacher with this username already exists.");
        }

        teacher.setPassword(passwordEncoder.encode(teacherRequestDto.getPassword()));

        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(savedTeacher);
    }

    @Transactional
    public TeacherResponseDto updateTeacher(Long id, TeacherRequestDto teacherRequestDto) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Teacher not found"));

        //Проверка и обновление полей
        if (teacherRequestDto.getUsername() != null && !teacherRequestDto.getUsername().isBlank()) {
            teacher.setUsername(teacherRequestDto.getUsername());
        }
        if (teacherRequestDto.getEmail() != null && !teacherRequestDto.getEmail().isBlank()) {
            if (!teacher.getEmail().equals(teacherRequestDto.getEmail()) &&
                    teacherRepository.existsTeacherByUsername(teacherRequestDto.getEmail())) {
                throw new RuntimeException("Email already exists.");
            }
            teacher.setEmail(teacherRequestDto.getEmail());
        }
        if (teacherRequestDto.getFullname() != null && !teacherRequestDto.getFullname().isBlank()) {
            teacher.setFullname(teacherRequestDto.getFullname());
        }

        teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    @Transactional
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Teacher not found"));

        teacherRepository.delete(teacher);
    }

}
