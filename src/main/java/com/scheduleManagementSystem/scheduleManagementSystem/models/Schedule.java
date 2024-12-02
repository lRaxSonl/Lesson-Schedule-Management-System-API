package com.scheduleManagementSystem.scheduleManagementSystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "schedule_group_teacher_subject", //Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "schedule_id"), //Внешний ключ для Schedule
            inverseJoinColumns = @JoinColumn(name = "group_teacher_subject_id") //Внешний ключ для GroupTeacherSubject
    )
    private Set<GroupTeacherSubject> groupTeacherSubjects = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private ClassRoom classeRoomId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;
}
