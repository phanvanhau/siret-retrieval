package com.vphan.microservices.siret.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
  private final String type;
  private final String title;
  private final HttpStatus httpStatus;

  public BusinessException() {
    this("internal.server.error", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
