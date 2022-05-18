package com.fleetlize.webapp.usecases;

public interface BasicCreate<T> {

  T execute(final T entity);

}
