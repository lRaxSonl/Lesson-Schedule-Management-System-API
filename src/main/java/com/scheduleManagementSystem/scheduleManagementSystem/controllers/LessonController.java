package com.scheduleManagementSystem.scheduleManagementSystem.controllers;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.LessonRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.LessonDetailsResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.LessonResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lesson")
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/")
    public List<LessonDetailsResponseDto> getAllSchedules() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public LessonDetailsResponseDto getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public LessonResponseDto createLesson(@RequestBody LessonRequestDto lessonRequestDto) {
        return lessonService.createLesson(lessonRequestDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LessonDetailsResponseDto updateLesson(@PathVariable Long id, @RequestBody LessonRequestDto lessonRequestDto) {
        return lessonService.updateLesson(id, lessonRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }
}
