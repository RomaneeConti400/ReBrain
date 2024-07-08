package com.example.rebrain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "note_id")
    private Integer id;

    @Column(name = "note_title")
    private String title;

    @Column(name = "note_text")
    private String text;

    @Column(name = "note_descr")
    private String descr;
}
