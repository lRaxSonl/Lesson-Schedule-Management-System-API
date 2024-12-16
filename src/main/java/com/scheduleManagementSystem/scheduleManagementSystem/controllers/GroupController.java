package com.scheduleManagementSystem.scheduleManagementSystem.controllers;


import com.scheduleManagementSystem.scheduleManagementSystem.dto.request.GroupRequestDto;
import com.scheduleManagementSystem.scheduleManagementSystem.dto.response.GroupResponseDto;
import com.scheduleManagementSystem.scheduleManagementSystem.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupResponseDto getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupResponseDto> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupResponseDto createGroup(@RequestBody GroupRequestDto groupRequestDto) {
        return groupService.createGroup(groupRequestDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GroupResponseDto updateGroup(@PathVariable Long id, @RequestBody GroupRequestDto groupRequestDto) {
        return groupService.updateGroup(id, groupRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
}
