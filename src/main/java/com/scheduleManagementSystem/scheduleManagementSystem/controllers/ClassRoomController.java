package com.scheduleManagementSystem.scheduleManagementSystem.controllers;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.ClassRoomRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.ClassRoomResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.services.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/classroom")
@RequiredArgsConstructor
public class ClassRoomController {
    private final ClassRoomService classRoomService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClassRoomResponseDto> getAllClassRooms() {
        return classRoomService.getAllClassRooms();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassRoomResponseDto getClassRoomById(@PathVariable Long id) {
        return classRoomService.getClassRoomById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ClassRoomResponseDto createClassRoom(@RequestBody ClassRoomRequestDto classRoomRequestDto) {
        return classRoomService.createClassRoom(classRoomRequestDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassRoomResponseDto updateClassRoom(
            @PathVariable Long id,
            @RequestBody ClassRoomRequestDto classRoomRequestDto) {
        return classRoomService.updateClassRoom(id, classRoomRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassRoom(@PathVariable Long id) {
        classRoomService.deleteClassRoom(id);
    }
}
