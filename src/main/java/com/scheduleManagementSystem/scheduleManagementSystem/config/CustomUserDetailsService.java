package com.scheduleManagementSystem.scheduleManagementSystem.config;

import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.StudentRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.TeacherRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Student;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Teacher
        Teacher teacher = teacherRepository.findByUsername(username).orElse(null);
        if (teacher != null) {
            return new User(teacher.getUsername(), teacher.getPassword(),
                    List.of(new SimpleGrantedAuthority(teacher.getRole())));
        }

        //Student
        Student student = studentRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("User not found"));

        return new User(student.getUsername(), student.getPassword(),
                List.of(new SimpleGrantedAuthority(student.getRole())));
    }
}
