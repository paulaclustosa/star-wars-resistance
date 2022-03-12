package com.letscode.starwarsresistance.exceptions;

import lombok.Getter;

import java.util.List;

public class BusinessValidatationException extends RuntimeException {

  @Getter
  private final List<String> validationErrors;

  public BusinessValidatationException(List<String> errors) {
    super(String.join(";", errors));
    this.validationErrors = errors;
  }

/*  public BusinessValidatationException(String errorMessage) {
    super(errorMessage);
    validationErrors = List.of(errorMessage);
  }*/

}
