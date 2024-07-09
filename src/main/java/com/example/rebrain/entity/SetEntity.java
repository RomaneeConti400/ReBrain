package com.example.rebrain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "set_id")
    private Integer id;

    @Column(name = "set_title")
    private String title;

    @Column(name = "set_description")
    private String description;
}
