package com.contentFormula.content.driverRaceresult.infraestructure.adapters.secondary;

import com.contentFormula.content.driverRaceresult.domain.model.DriverRaceResult;
import com.contentFormula.content.driverRaceresult.domain.port.out.DriverRaceResultRepositoryPort;
import com.contentFormula.content.driverRaceresult.infraestructure.entities.DriverRaceResultEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class JpaDriverRaceResultRepositoryAdapter implements DriverRaceResultRepositoryPort {
    @Autowired
    private final JpaDriverRaceResultRepository jpaDriverRaceResultRepository;


    @Override
    public Optional<DriverRaceResult> save(DriverRaceResult driverRaceResult) {
        DriverRaceResultEntity entity = DriverRaceResultMapper.toEntity(driverRaceResult);
        DriverRaceResultEntity savedEntity = jpaDriverRaceResultRepository.save(entity);
        return Optional.of(DriverRaceResultMapper.toDomain(savedEntity));
    }


    @Override
    public Optional<DriverRaceResult> findById(Long id) {
        return jpaDriverRaceResultRepository.findById(id).map(DriverRaceResultMapper::toDomain);
    }

    @Override
    public Optional<DriverRaceResult> findByDate(String date) {
        return jpaDriverRaceResultRepository.findByDate(date).map(DriverRaceResultMapper::toDomain);
    }

    @Override
    public List<DriverRaceResult> findResultsByFullName(String fullName) {
        return jpaDriverRaceResultRepository.findByDriverName(fullName).stream().map(DriverRaceResultMapper::toDomain).toList();
    }

    @Override
    public Optional<DriverRaceResult> findByRaceNameAndDriverInfoId(String raceName, Long driverId) {
        return jpaDriverRaceResultRepository.findByRaceNameAndDriverId(raceName,driverId).map(DriverRaceResultMapper::toDomain);
    }


}
