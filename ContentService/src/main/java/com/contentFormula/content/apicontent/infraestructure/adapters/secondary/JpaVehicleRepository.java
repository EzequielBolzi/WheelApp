package com.contentFormula.content.apicontent.infraestructure.adapters.secondary;

import com.contentFormula.content.apicontent.infraestructure.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaVehicleRepository extends JpaRepository <VehicleEntity,Long> {
    Optional<VehicleEntity> findByChassis(String chassis);
}
