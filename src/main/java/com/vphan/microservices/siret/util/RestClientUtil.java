package com.vphan.microservices.siret.util;

import org.slf4j.Logger;
import org.springframework.web.client.RestClientException;

import java.util.concurrent.TimeoutException;

public class RestClientUtil {
  public static void logWebClientError(Logger logger, String uri, Throwable t) {
    if (t instanceof RestClientException) {
      RestClientException e = (RestClientException) t;
      logger.error("REST error for {} - reason: {} - details: {}", uri, e.getLocalizedMessage(), e.getRootCause());
    } else if (t instanceof TimeoutException) {
      logger.error("Timeout error for {}", uri, t);
    } else {
      logger.error("Error while calling {}", uri, t);
    }
  }
}
