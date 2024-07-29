package com.contentFormula.content.apicontent.infraestructure.adapters.secondary;

import com.contentFormula.content.apicontent.domain.dtos.DriverInfoDto;
import com.contentFormula.content.apicontent.domain.model.DriverInfo;
import com.contentFormula.content.apicontent.domain.port.out.DriverInfoRepositoryPort;
import com.contentFormula.content.apicontent.infraestructure.entities.DriverInfoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class JpaDriverInfoAdapter implements DriverInfoRepositoryPort {

    private final JpaDriverInfoRepository jpaDriverInfoRepository;

    @Override
    public Optional<DriverInfo> updateDriverInfoInDB(Long driverId, DriverInfo driverInfo) {
        return jpaDriverInfoRepository.findById(driverId)
                .map(existingEntity -> {
                    DriverInfoEntity updatedEntity = DriverInfoMapper.toEntity(driverInfo);
                    updatedEntity.setDriverIdentifier(driverId);
                    return jpaDriverInfoRepository.save(updatedEntity);
                })
                .map(DriverInfoMapper::toDomain);
    }

    @Override
    public Optional<DriverInfo> saveDriverInfoInDB(DriverInfo driverInfo) {
        DriverInfoEntity driverInfoEntity = DriverInfoMapper.toEntity(driverInfo);
        DriverInfoEntity savedDriverInfoEntity = jpaDriverInfoRepository.save(driverInfoEntity);
        return Optional.of(DriverInfoMapper.toDomain(savedDriverInfoEntity));
    }

    @Override
    public List<DriverInfo> getAllDriversFromDB() {
        return jpaDriverInfoRepository.findAll().stream()
                .map(DriverInfoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DriverInfo> getDriverInfoFromDB(Long driverId) {
        return jpaDriverInfoRepository.findById(driverId)
                .map(DriverInfoMapper::toDomain);
    }

    //Todo VERIFY THIS  METHOD

    @Override
    public List<DriverInfo> getDriversByTeamFromDB(String team) {
        return jpaDriverInfoRepository.findByVehiclesTeam(team).stream()
                .map(DriverInfoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DriverInfo> findByNameFromDB(String driverName) {
        return jpaDriverInfoRepository.findByFullName(driverName)
                .map(DriverInfoMapper::toDomain);
    }
}