package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findByLessonId(Long lessonId);

    List<Schedule> findAllByLessonId(Long lessonId);
}
