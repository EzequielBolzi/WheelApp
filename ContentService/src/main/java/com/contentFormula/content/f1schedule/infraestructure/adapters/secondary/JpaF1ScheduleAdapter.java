package com.contentFormula.content.f1schedule.infraestructure.adapters.secondary;

import com.contentFormula.content.f1schedule.domain.model.F1Schedule;
import com.contentFormula.content.f1schedule.domain.port.out.F1ScheduleRepositoryPort;
import com.contentFormula.content.f1schedule.infraestructure.entities.F1ScheduleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class JpaF1ScheduleAdapter implements F1ScheduleRepositoryPort {

    private final JpaF1ScheduleRepository jpaF1ScheduleRepository;

    @Override
    public Optional<F1Schedule> saveSchedule(F1Schedule f1Schedule) {
        F1ScheduleEntity f1ScheduleEntity = F1ScheduleMapper.toEntity(f1Schedule);
        F1ScheduleEntity savedF1ScheduleEntity = jpaF1ScheduleRepository.save(f1ScheduleEntity);
        return Optional.of(F1ScheduleMapper.toDomain(savedF1ScheduleEntity));
    }


    @Override
    public List<F1Schedule> getScheduleByYear(Integer year) {
        return jpaF1ScheduleRepository.findByYear(year).stream().map(F1ScheduleMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<F1Schedule> getAllSchedule() {
        return jpaF1ScheduleRepository.findAll()
                .stream()
                .map(F1ScheduleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<F1Schedule> getScheduleByDate(ZonedDateTime date) {
        F1ScheduleEntity f1ScheduleEntity = jpaF1ScheduleRepository.findScheduleByDate(date);
        return Optional.ofNullable(F1ScheduleMapper.toDomain(f1ScheduleEntity));
    }

    @Override
    public void updateSchedule(F1Schedule updatedSchedule, Long id) {
        jpaF1ScheduleRepository.findById(id)
                .ifPresent(existingEntity -> {
                    if (updatedSchedule.getStartDate() != null) {
                        existingEntity.setStartDate(updatedSchedule.getStartDate());
                    }
                    if (updatedSchedule.getEndDate() != null) {
                        existingEntity.setEndDate(updatedSchedule.getEndDate());
                    }
                    existingEntity.setCompleted(updatedSchedule.isCompleted());
                    if (updatedSchedule.getGrandPrix() != null) {
                        existingEntity.setGrandPrix(updatedSchedule.getGrandPrix());
                    }
                    if (updatedSchedule.getCircuit() != null) {
                        existingEntity.setCircuit(updatedSchedule.getCircuit());
                    }
                    existingEntity.setPostponedOrCanceled(updatedSchedule.isPostponedOrCanceled());
                    if (updatedSchedule.getWinner() != null) {
                        existingEntity.setWinner(updatedSchedule.getWinner());
                    }
                    if (updatedSchedule.getWinner() != null) {
                        existingEntity.setRaceId(updatedSchedule.getRaceId());
                    }
                    jpaF1ScheduleRepository.save(existingEntity);
                });
    }

}
