package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.models.Group;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Boolean existsGroupByName(String name);
    Group findByName(String name);
    Group findByStudentsContains(Student student);
}
