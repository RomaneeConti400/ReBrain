package com.example.rebrain.dto;

import com.example.rebrain.entity.SetEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetDto {
    private Integer id;
    private String title;
    private String description;
    private Integer userId;
}
