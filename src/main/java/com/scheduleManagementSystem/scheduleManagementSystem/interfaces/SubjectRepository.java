package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Boolean existsSubjectByName(String name);
}
