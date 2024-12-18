package com.scheduleManagementSystem.scheduleManagementSystem.services;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.SubjectRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.SubjectResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.SubjectMapper;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.SubjectRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Subject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    List<SubjectResponseDto> getAllSubjects() {
        return subjectRepository.findAll().stream().map(subjectMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public SubjectResponseDto createSubject(SubjectRequestDto subjectRequestDto) {
        Subject subject = subjectMapper.toEntity(subjectRequestDto);

        if (!subjectRepository.existsSubjectByName(subject.getName())) {
            Subject savedSubject = subjectRepository.save(subject);
            return subjectMapper.toDto(savedSubject);
        } else {
            throw new RuntimeException("Subject with this name is already exists.");
        }
    }

    @Transactional
    public SubjectResponseDto updateSubject(Long id, SubjectRequestDto subjectRequestDto) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Subject with this id does not exist."));

        if (!subject.getName().equals(subjectRequestDto.getName()) &&
                subjectRepository.existsSubjectByName(subjectRequestDto.getName())) {
            throw new RuntimeException("Subject with this name already exists.");
        }

        subject.setName(subjectRequestDto.getName());
        subjectRepository.save(subject);

        return subjectMapper.toDto(subject);
    }

    @Transactional
    public void deleteSubject(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Subject with this id does not exist."));

        subjectRepository.delete(subject);
    }
}
