package com.example.rebrain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulePostDto {
    private Long setId;
    private List<String> repeats;

    public SchedulePostDto(ScheduleRequestDto request) {
        this.setId = request.getSetId();
        this.repeats = request.getRepeats().stream()
                .flatMap(repeat -> repeat.getTimes().stream()
                        .map(time -> repeat.getDay() + " " + time.getTime().replace(":", "")))
                .toList();
    }
}
