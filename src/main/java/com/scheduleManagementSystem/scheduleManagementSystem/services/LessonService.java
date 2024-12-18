package com.scheduleManagementSystem.scheduleManagementSystem.services;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.LessonRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.LessonDetailsResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.LessonResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.LessonMapper;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.LessonRepository;
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
    private final LessonMapper lessonMapper;

    @Transactional
    public LessonResponseDto createLesson(LessonRequestDto dto) {
        Lesson lesson = lessonMapper.toEntity(dto);
        Lesson savedLesson = lessonRepository.save(lesson);
        return lessonMapper.toDto(savedLesson);
    }

    public List<LessonDetailsResponseDto> getAllLessons() {
        return lessonRepository.findAll().stream()
                .map(lessonMapper::toDetailsDto)
                .collect(Collectors.toList());
    }
}
