package com.fleetlize.rating.gateway.data.repositories;

import com.fleetlize.rating.gateway.data.entities.CarEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
  Optional<CarEntity> findByPlate(final String plate);
}
