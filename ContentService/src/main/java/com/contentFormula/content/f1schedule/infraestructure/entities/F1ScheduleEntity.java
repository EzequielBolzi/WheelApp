package com.contentFormula.content.f1schedule.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "f1_schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class F1ScheduleEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "grand_prix")
    private String grandPrix;

    @Column(name = "circuit")
    private String circuit;


    @Column(name = "is_postponed_or_canceled")
    private boolean isPostponedOrCanceled;

    @Column(name = "winner")
    private String winner;


}