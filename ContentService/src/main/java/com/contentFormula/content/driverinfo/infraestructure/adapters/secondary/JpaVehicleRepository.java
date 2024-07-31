package com.contentFormula.content.driverinfo.infraestructure.adapters.secondary;

import com.contentFormula.content.driverinfo.infraestructure.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaVehicleRepository extends JpaRepository <VehicleEntity,Long> {
    Optional<VehicleEntity> findByChassis(String chassis);

    List<VehicleEntity> findByTeam(String team);
}
