package com.fleetlize.rating.gateway.data.repositories;

import com.fleetlize.rating.gateway.data.entities.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RateEntity, Long> {
}
