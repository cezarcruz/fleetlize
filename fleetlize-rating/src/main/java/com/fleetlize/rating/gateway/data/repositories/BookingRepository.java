package com.fleetlize.rating.gateway.data.repositories;

import com.fleetlize.rating.gateway.data.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

}
