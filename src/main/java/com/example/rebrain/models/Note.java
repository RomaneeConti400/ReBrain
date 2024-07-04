package com.example.rebrain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Note {
    private Integer id;
    private String title;
    private String text;
    private String descr;
}
