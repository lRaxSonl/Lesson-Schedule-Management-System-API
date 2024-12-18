package com.scheduleManagementSystem.scheduleManagementSystem.dto.response;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Group;
import lombok.Data;


@Data
public class StudentResponseDto {
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private Group group;
}
