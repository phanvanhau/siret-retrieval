package com.vphan.microservices.learning.exception;

import lombok.Data;

@Data
public class BusinessExceptionResponse {
  private final String type;
  private final String title;
  private final int status;

  public BusinessExceptionResponse(BusinessException e) {
    this.type = e.getType();
    this.title = e.getTitle();
    this.status = e.getHttpStatus().value();
  }
}
