package com.example.rebrain.controllers;

import com.example.rebrain.dto.ScheduleDto;
import com.example.rebrain.dto.SchedulePostDto;
import com.example.rebrain.entity.ScheduleEntity;
import com.example.rebrain.mapper.ScheduleMapper;
import com.example.rebrain.services.ScheduleService;
import com.example.rebrain.util.CronValidator;
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
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody SchedulePostDto scheduleDto) {
        String cron = scheduleDto.getCron();
        if (!CronValidator.isValidCron(cron)) {
            log.error("Invalid cron expression: {}", cron);
            return ResponseEntity.badRequest().body(null);
        }
        log.debug("Creating schedule with data: {}", scheduleDto);
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setSetId(scheduleDto.getSetId());
        scheduleEntity.setCron(cron);
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
