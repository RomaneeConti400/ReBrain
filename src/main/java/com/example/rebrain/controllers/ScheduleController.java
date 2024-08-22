package com.example.rebrain.controllers;

import com.example.rebrain.dto.ScheduleDto;
import com.example.rebrain.dto.SchedulePostDto;
import com.example.rebrain.dto.ScheduleRequestDto;
import com.example.rebrain.entity.ScheduleEntity;
import com.example.rebrain.mapper.ScheduleMapper;
import com.example.rebrain.services.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        SchedulePostDto schedulePostDto = new SchedulePostDto(requestDto);
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setSetId(schedulePostDto.getSetId());
        scheduleEntity.setRepeats(schedulePostDto.getRepeats());
        log.debug("Creating schedule with data: {}", scheduleEntity);
        ScheduleEntity createdSchedule = scheduleService.create(scheduleEntity);
        ScheduleDto createdToDto = ScheduleMapper.toDto(createdSchedule);
        URI location = URI.create("/schedules/" + createdToDto.getId());
        log.info("Schedule created with ID: {}", createdToDto.getId());
        return ResponseEntity.created(location).body(createdToDto);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getAllSchedules() {
        log.debug("Fetching all schedules");
        List<ScheduleEntity> schedulesEntities = scheduleService.getAll();
        List<ScheduleDto> scheduleDtos = schedulesEntities.stream()
                .map(ScheduleMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Returning {} schedules", scheduleDtos.size());
        return ResponseEntity.ok(scheduleDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable Long id) {
        log.debug("Fetching schedule with ID: {}", id);
        ScheduleDto schedule = ScheduleMapper.toDto(scheduleService.getOne(id));
        log.debug("Schedule found: {}", schedule);
        return ResponseEntity.ok(schedule);
    }
}
