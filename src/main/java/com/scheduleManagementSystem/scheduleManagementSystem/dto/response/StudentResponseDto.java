package com.scheduleManagementSystem.scheduleManagementSystem.dto.response;
import lombok.Data;


@Data
public class StudentResponseDto {
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String groupName;
}
