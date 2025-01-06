package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;

import com.scheduleManagementSystem.scheduleManagementSystem.models.NotificationRecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface NotificationRecipientRepository extends JpaRepository<NotificationRecipient, Integer> {
    List<NotificationRecipient> findByStudentId(Long studentId);
    List<NotificationRecipient> findByTeacherId(Long teacherId);
}
