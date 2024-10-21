package com.scheduleManagementSystem.scheduleManagementSystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "fullname", nullable = false, length = 100)
    private String fullname;

    @ManyToMany
    @JoinTable(
            name = "student_groups", //Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "student_id"), //Внешний ключ для Student
            inverseJoinColumns = @JoinColumn(name = "group_id") //Внешний ключ для Group
    )
    private Set<Group> groups = new HashSet<>();
}
