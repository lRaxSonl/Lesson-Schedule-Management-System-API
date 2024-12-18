package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Boolean existsStudentByEmail(String email);
    Boolean existsStudentByUsername(String username);

    Optional<Student> findByUsername(String username);
}
