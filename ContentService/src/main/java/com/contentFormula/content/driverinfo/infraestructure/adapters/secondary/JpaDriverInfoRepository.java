package com.contentFormula.content.driverinfo.infraestructure.adapters.secondary;

import com.contentFormula.content.driverinfo.infraestructure.entities.DriverInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaDriverInfoRepository extends JpaRepository<DriverInfoEntity,Long>{

    Optional<DriverInfoEntity> findByFullName(String name);

    //Find but by Driver_Id from each driver not from mine generate id
    @Query("SELECT d FROM DriverInfoEntity d WHERE d.driverId = :driverId")
    Optional<DriverInfoEntity> findById(@Param("driverId") Long driverId);


}
