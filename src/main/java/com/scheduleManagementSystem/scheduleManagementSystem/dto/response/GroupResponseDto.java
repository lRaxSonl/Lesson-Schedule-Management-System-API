package com.scheduleManagementSystem.scheduleManagementSystem.dto.response;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Student;
import lombok.Data;
import java.util.List;


@Data
public class GroupResponseDto {
    private Long id;
    private String name;
    private List<StudentResponseDto> students;
}
