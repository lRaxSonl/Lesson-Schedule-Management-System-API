package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
