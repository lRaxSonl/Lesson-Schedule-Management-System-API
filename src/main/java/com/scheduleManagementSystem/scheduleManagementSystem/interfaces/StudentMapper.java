package com.scheduleManagementSystem.scheduleManagementSystem.interfaces;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.StudentRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.StudentResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Group;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Student;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentResponseDto toDto(Student student);
    Student toEntity(StudentRequestDto studentRequestDto);

    default Group map(Long value) {
        if (value == null) {
            return null;
        }
        Group group = new Group();
        group.setId(value);
        return group;
    }
}
