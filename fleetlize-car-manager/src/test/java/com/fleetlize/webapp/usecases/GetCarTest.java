package com.fleetlize.webapp.usecases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.fleetlize.webapp.entities.Car;
import com.fleetlize.webapp.gateways.database.repositories.CarRepository;
import com.fleetlize.webapp.usecases.impl.GetCar;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetCarTest {

  @Mock
  private CarRepository carRepository;

  @InjectMocks
  private GetCar getCar;

  @Test
  public void shouldGetACar() {
    final Car car = Car.builder()
        .id(1L)
        .mileage(0)
        .plate("CCC1231")
        .build();
    when(carRepository.findBy(1L)).thenReturn(car);

    final Optional<Car> execute = getCar.execute(1L);

    execute.ifPresent(c -> {
      assertThat(c, notNullValue());
      assertThat(c.getId(), is(1L));
    });

    verify(carRepository, times(1)).findBy(1L);
    verifyNoMoreInteractions(carRepository);

  }

}
