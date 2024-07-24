package com.example.rebrain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetGetDto {
    private Long setId;
    private String setTitle;
    private String setDescription;
    private List<CardDto> cards;
}