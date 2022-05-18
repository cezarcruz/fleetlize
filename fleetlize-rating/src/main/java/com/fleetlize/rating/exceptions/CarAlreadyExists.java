package com.fleetlize.rating.exceptions;

public class CarAlreadyExists extends RuntimeException {
    public CarAlreadyExists() {
        super("car already exists");
    }
}
