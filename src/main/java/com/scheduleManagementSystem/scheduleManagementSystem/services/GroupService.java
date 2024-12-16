package com.scheduleManagementSystem.scheduleManagementSystem.services;

import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.GroupRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.GroupResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.GroupMapper;
import com.scheduleManagementSystem.scheduleManagementSystem.interfaces.GroupRepository;
import com.scheduleManagementSystem.scheduleManagementSystem.models.Group;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupResponseDto getGroupById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Group not found"));

        GroupResponseDto groupResponseDto = groupMapper.toDto(group);
        return groupResponseDto;
    }

    public List<GroupResponseDto> getAllGroups() {
        return groupRepository.findAll().stream().map(groupMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public GroupResponseDto createGroup(GroupRequestDto groupRequestDto) {
        Group group = groupMapper.toEntity(groupRequestDto);

        if (!groupRepository.existsGroupByName(group.getName())) {
            Group savedGroup = groupRepository.save(group);
            return groupMapper.toDto(savedGroup);
        } else {
            throw new RuntimeException("Group with this name is already exists.");
        }
    }

    @Transactional
    public GroupResponseDto updateGroup(Long id, GroupRequestDto groupRequestDto) {
        Group group = groupRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Group with this id does not exist."));

        if (!group.getName().equals(groupRequestDto.getName()) &&
                groupRepository.existsGroupByName(groupRequestDto.getName())) {
            throw new RuntimeException("Group with this name already exists.");
        }

        group.setName(groupRequestDto.getName());
        groupRepository.save(group);

        return groupMapper.toDto(group);
    }

    @Transactional
    public void deleteGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Group with this id does not exist."));

        groupRepository.delete(group);
    }
}
