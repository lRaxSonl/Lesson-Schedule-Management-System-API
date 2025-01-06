package com.scheduleManagementSystem.scheduleManagementSystem.services;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.LessonRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.LessonDetailsResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.LessonResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.*;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Lesson;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final LessonMapper lessonMapper;


    @Transactional
    public LessonResponseDto createLesson(LessonRequestDto dto) {
        Lesson lesson = lessonMapper.toEntity(dto);

        lesson.setTeacher(teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found")));
        lesson.setSubject(subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found")));
        lesson.setGroup(groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found")));


        Lesson savedLesson = lessonRepository.save(lesson);
        return lessonMapper.toDto(savedLesson);
    }

    public List<LessonDetailsResponseDto> getAllLessons() {
        return lessonRepository.findAll().stream()
                .map(lessonMapper::toDetailsDto)
                .collect(Collectors.toList());
    }

    public LessonDetailsResponseDto getLessonById(Long id) {
        return lessonMapper.toDetailsDto(lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found")));
    }

    @Transactional
    public LessonDetailsResponseDto updateLesson(Long id, LessonRequestDto dto) {
        Lesson existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        existingLesson.setTeacher(teacherRepository.findById(dto.getTeacherId()).orElseThrow(() ->
                new RuntimeException("Teacher not found.")
        ));
        existingLesson.setSubject(subjectRepository.findById(dto.getSubjectId()).orElseThrow(() ->
                new RuntimeException("Subject not found.")
        ));
        existingLesson.setGroup(groupRepository.findById(dto.getGroupId()).orElseThrow(()->
                new RuntimeException("Group not found.")
        ));

        Lesson updatedLesson = lessonRepository.save(existingLesson);

        // Вернуть обновленные данные
        return lessonMapper.toDetailsDto(updatedLesson);
    }


    public List<Lesson> getLessonsByTeacherId(Long teacherId) {
        return lessonRepository.findAllByTeacherId(teacherId);
    }


    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
}
