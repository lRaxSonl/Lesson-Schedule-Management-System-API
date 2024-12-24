package com.scheduleManagementSystem.scheduleManagementSystem.controllers;


import com.scheduleManagementSystem.scheduleManagementSystem.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
}
