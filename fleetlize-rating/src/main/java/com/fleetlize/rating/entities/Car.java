package com.fleetlize.rating.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Car {
    private Long id;
    private String plate;
}
