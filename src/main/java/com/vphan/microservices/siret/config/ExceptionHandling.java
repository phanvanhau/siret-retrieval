package com.vphan.microservices.siret.config;

import com.vphan.microservices.siret.exception.BusinessException;
import com.vphan.microservices.siret.exception.BusinessExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandling {
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<BusinessExceptionResponse> handleBusinessException(BusinessException e) {
    logException(e);
    return ResponseEntity.status(e.getHttpStatus()).body(new BusinessExceptionResponse(e));
  }

  private void logException(BusinessException e) {
    log.error("A business exception was thrown: {}", e.getMessage());
    if (e.getCause() != null) {
      log.error("Business exception '{}' caused by: ", e.getMessage(), e.getCause());
    }
  }
}
