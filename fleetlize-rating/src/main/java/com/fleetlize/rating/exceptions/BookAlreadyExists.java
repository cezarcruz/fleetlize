package com.fleetlize.rating.exceptions;

public class BookAlreadyExists extends RuntimeException {
  public BookAlreadyExists() {
    super("booking already exists");
  }
}
