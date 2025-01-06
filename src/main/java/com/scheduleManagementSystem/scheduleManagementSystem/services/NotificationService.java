package com.scheduleManagementSystem.scheduleManagementSystem.services;

import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.*;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Notification;
import com.scheduleManagementSystem.scheduleManagementSystem.models.NotificationRecipient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationRecipientRepository notificationRecipientRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public Notification createNotification(String message, List<Long> studentIds, List<Long> teacherIds) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());

        Notification savedNotification = notificationRepository.save(notification);

        if (studentIds != null) {
            studentIds.forEach(studentId -> {
                NotificationRecipient recipient = new NotificationRecipient();
                recipient.setStudent(studentRepository.findById(studentId).orElseThrow(() ->
                        new RuntimeException("Student not found.")));

                recipient.setNotification(savedNotification);
                notificationRecipientRepository.save(recipient);
            });
        }

        if (teacherIds != null) {
            teacherIds.forEach(teacherId -> {
                NotificationRecipient recipient = new NotificationRecipient();
                recipient.setTeacher(teacherRepository.findById(teacherId).orElseThrow(() ->
                        new RuntimeException("Teacher not found.")));

                recipient.setNotification(savedNotification);
                notificationRecipientRepository.save(recipient);
            });
        }

        return savedNotification;
    }

    public List<NotificationRecipient> getNotificationsForStudent(Long studentId) {
        return notificationRecipientRepository.findByStudentId(studentId);
    }

    public List<NotificationRecipient> getNotificationsForTeacher(Long teacherId) {
        return notificationRecipientRepository.findByTeacherId(teacherId);
    }
}
