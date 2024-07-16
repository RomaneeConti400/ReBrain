package com.example.rebrain.dto;

import com.example.rebrain.entity.CardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Integer id;
    private String title;
    private String description;
    private Integer userId;
}
