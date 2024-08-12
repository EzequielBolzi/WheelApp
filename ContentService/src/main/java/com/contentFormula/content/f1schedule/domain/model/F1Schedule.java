package com.contentFormula.content.f1schedule.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class F1Schedule {
    private Long id;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private boolean completed;
    private String grandPrix;
    private String circuit;
    private boolean isPostponedOrCanceled;
    private String winner;
    private String raceId;

}
