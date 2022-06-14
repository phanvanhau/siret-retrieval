package com.vphan.microservices.siret.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ServiceAHealthCheck implements HealthIndicator {
  private final String message_key = "SIRET microservice ";
  @Override
  public Health health() {
    if (!isServiceRunning()) {
      return Health.down().withDetail(message_key, "Not Available").build();
    }
    return Health.up().withDetail(message_key, "Available").build();
  }

  private Boolean isServiceRunning() {
    // Logic Skipped
    return true;
  }
}
