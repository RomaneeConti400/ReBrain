package com.example.rebrain.services;

import com.example.rebrain.entity.ScheduleEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.repositories.ScheduleRepo;
import com.example.rebrain.util.ThreadLocalUserIdHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;

    public ScheduleEntity create(ScheduleEntity scheduleEntity) {
        Long userId = Long.valueOf(ThreadLocalUserIdHolder.get());
        scheduleEntity.setUserId(userId);
        scheduleEntity.setStartDate(LocalDate.now());
        return scheduleRepo.save(scheduleEntity);
    }

    public List<ScheduleEntity> getAll() {
        return scheduleRepo.findAll();
    }

    public ScheduleEntity getOne(Long id) throws ObjectNotFoundException {
        return scheduleRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Schedule with ID " + id + " not found"));
    }

    public ScheduleEntity update(Long id, ScheduleEntity updateEntity) throws ObjectNotFoundException {
        ScheduleEntity scheduleEntity = getEntityById(id);
        if (updateEntity.getRepeats() != null) {
            scheduleEntity.setRepeats(updateEntity.getRepeats());
        }
        return scheduleRepo.save(scheduleEntity);
    }

    public void delete(Long id) throws ObjectNotFoundException {
        ScheduleEntity scheduleEntity = getEntityById(id);
        scheduleRepo.delete(scheduleEntity);
    }

    private ScheduleEntity getEntityById(Long id) throws ObjectNotFoundException {
        Optional<ScheduleEntity> scheduleOptional = scheduleRepo.findById(id);
        return scheduleOptional.orElseThrow(() -> new ObjectNotFoundException("Schedule with ID " + id + " not found"));
    }
}