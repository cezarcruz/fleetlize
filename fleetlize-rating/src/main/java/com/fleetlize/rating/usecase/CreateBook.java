package com.fleetlize.rating.usecase;

import com.fleetlize.rating.entities.Booking;
import com.fleetlize.rating.exceptions.BookAlreadyExists;
import com.fleetlize.rating.gateway.data.repositories.BookingRepository;
import com.fleetlize.rating.usecase.mapper.BookingEntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CreateBook {

  private BookingRepository bookingRepository;
  private BookingEntityMapper bookingMapper;

  public Booking execute(final Booking booking) {

    bookingRepository.findById(booking.getId()).ifPresent( b -> {
      throw new BookAlreadyExists();
    });

    final var bookingEntity = bookingMapper.from(booking);
    final var bookSaved = bookingRepository.save(bookingEntity);
    return bookingMapper.from(bookSaved);
  }

}
