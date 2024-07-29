package com.contentFormula.content.apicontent.infraestructure.adapters.secondary;

import com.contentFormula.content.apicontent.infraestructure.entities.DriverInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaDriverInfoRepository extends JpaRepository<DriverInfoEntity,Long>{
    List<DriverInfoEntity> findByVehiclesTeam(String team);
    Optional<DriverInfoEntity> findByFullName(String name);

    //Find but by Driver_Id from each driver not from mine DB
    @Query("SELECT d FROM DriverInfoEntity d WHERE d.driverId = :driverId")
    Optional<DriverInfoEntity> findById(@Param("driverId") Long driverId);


}
