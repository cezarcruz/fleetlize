package com.fleetlize.rating.usecase;

import com.fleetlize.rating.entities.Rate;
import com.fleetlize.rating.gateway.data.entities.RateEntity;
import com.fleetlize.rating.gateway.data.repositories.BookingRepository;
import com.fleetlize.rating.gateway.data.repositories.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RateCar {

  private RatingRepository ratingRepository;
  private BookingRepository bookingRepository;

  public Rate execute(final Rate rate) {
    final var booking = bookingRepository.findById(rate.getBooking().getId());

    var rateEntity = booking.map(b -> RateEntity.builder()
            .booking(b)
            .score(rate.getScore())
            .build()).orElseThrow();

    final var rateSaved = ratingRepository.save(rateEntity);
    return rate.toBuilder().id(rateSaved.getId()).build();

  }

}
