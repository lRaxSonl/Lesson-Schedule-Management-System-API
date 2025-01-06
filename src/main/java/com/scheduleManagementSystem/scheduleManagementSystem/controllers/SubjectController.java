package com.scheduleManagementSystem.scheduleManagementSystem.controllers;


import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.SubjectRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.SubjectResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectResponseDto> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectResponseDto createSubject(@RequestBody SubjectRequestDto subjectRequestDto) {
        return subjectService.createSubject(subjectRequestDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubjectResponseDto updateSubject(@PathVariable Long id, @RequestBody SubjectRequestDto subjectRequestDto) {
        return subjectService.updateSubject(id, subjectRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }
}
