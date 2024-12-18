package com.scheduleManagementSystem.scheduleManagementSystem.services;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.StudentRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.StudentResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.GroupRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.StudentMapper;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.StudentRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Group;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Student;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final GroupRepository groupRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Student not found"));

        StudentResponseDto dto = studentMapper.toDto(student);
        return dto;
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toDto).collect(Collectors.toList());
    }


    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        Student student = studentMapper.toEntity(studentRequestDto);

        if (studentRepository.existsStudentByEmail(student.getEmail())) {
            throw new RuntimeException("Student with this email already exists.");
        }else if (studentRepository.existsStudentByUsername(student.getUsername())) {
            throw new RuntimeException("Student with this username already exists.");
        }

        Group group = groupRepository.findById(studentRequestDto.getGroup())
                .orElseThrow(() -> new RuntimeException("Group with id: " + student.getGroup() + " not found"));
        student.setGroup(group);


        student.setPassword(passwordEncoder.encode(studentRequestDto.getPassword()));

        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }


    @Transactional
    public StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto) {
        Student student = studentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Student not found"));

        //Проверка и обновление данных
        if (studentRequestDto.getUsername() != null && !studentRequestDto.getUsername().isBlank()) {
            student.setUsername(studentRequestDto.getUsername());
        }
        if (studentRequestDto.getEmail() != null && !studentRequestDto.getEmail().isBlank()) {
            if (!student.getEmail().equals(studentRequestDto.getEmail()) &&
                    studentRepository.existsStudentByEmail(studentRequestDto.getEmail())) {
                throw new RuntimeException("Email already exists.");
            }
            student.setEmail(studentRequestDto.getEmail());
        }
        if (studentRequestDto.getFullname() != null && !studentRequestDto.getFullname().isBlank()) {
            student.setFullname(studentRequestDto.getFullname());
        }
        if (studentRequestDto.getGroup() != null) {
            student.setGroup(groupRepository.findById(studentRequestDto.getGroup()).orElseThrow(() ->
                    new RuntimeException("Group with this id does not exists.")));
        }

        studentRepository.save(student);
        return studentMapper.toDto(student);
    }


    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Student with this id does not exists."));

        studentRepository.delete(student);
    }
}
