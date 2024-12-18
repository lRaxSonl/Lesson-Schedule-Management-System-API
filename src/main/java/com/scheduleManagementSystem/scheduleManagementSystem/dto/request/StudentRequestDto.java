package com.scheduleManagementSystem.scheduleManagementSystem.dto.request;

import lombok.Data;


@Data
public class StudentRequestDto {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private Long group;
}
