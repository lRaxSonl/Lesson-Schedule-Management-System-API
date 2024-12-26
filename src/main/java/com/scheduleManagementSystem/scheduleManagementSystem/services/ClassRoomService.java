package com.scheduleManagementSystem.scheduleManagementSystem.services;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.ClassRoomRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.ClassRoomResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.ClassRoomMapper;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.ClassRoomRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.models.ClassRoom;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassRoomService {
    private final ClassRoomRepository classRoomRepository;
    private final ClassRoomMapper classRoomMapper;

    public ClassRoomResponseDto getClassRoomById(Long id) {
        ClassRoom classRoom = classRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found."));
        return classRoomMapper.toDto(classRoom);
    }


    public List<ClassRoomResponseDto> getAllClassRooms() {
        return classRoomRepository.findAll().stream()
                .map(classRoomMapper::toDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public ClassRoomResponseDto createClassRoom(ClassRoomRequestDto dto) {
        ClassRoom classRoom = classRoomMapper.toEntity(dto);
        ClassRoom savedClassRoom = classRoomRepository.save(classRoom);
        return classRoomMapper.toDto(savedClassRoom);
    }


    @Transactional
    public ClassRoomResponseDto updateClassRoom(Long id, ClassRoomRequestDto dto) {
        ClassRoom classRoom = classRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found."));

        classRoom.setName(dto.getName());
        classRoom.setLocation(dto.getLocation());
        ClassRoom updatedClassRoom = classRoomRepository.save(classRoom);

        return classRoomMapper.toDto(updatedClassRoom);
    }


    public void deleteClassRoom(Long id) {
        ClassRoom classRoom = classRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found."));
        classRoomRepository.delete(classRoom);
    }
}
