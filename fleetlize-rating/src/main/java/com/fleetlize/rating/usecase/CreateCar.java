package com.fleetlize.rating.usecase;


import com.fleetlize.rating.entities.Car;
import com.fleetlize.rating.exceptions.CarAlreadyExists;
import com.fleetlize.rating.gateway.data.repositories.CarRepository;
import com.fleetlize.rating.usecase.mapper.CarEntityMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateCar {

    private CarRepository carRepository;
    private CarEntityMapper carEntityMapper;

    public CreateCar(final CarRepository carRepository,
                     final CarEntityMapper carEntityMapper) {
        this.carRepository = carRepository;
        this.carEntityMapper = carEntityMapper;
    }

    public Car execute(final Car car) {

        carRepository.findByPlate(car.getPlate())
                .ifPresent( c -> {
                    throw new CarAlreadyExists();
                });

        final var save = carRepository.save(carEntityMapper.from(car));

        return carEntityMapper.from(save);

    }

}
