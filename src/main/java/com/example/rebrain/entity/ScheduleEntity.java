package com.example.rebrain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "schedules")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "set_id")
    private Long title;

    @Column(name = "cron_expression")
    private String cron;

    @Column(name = "start_date")
    private LocalDate startDate;
}
