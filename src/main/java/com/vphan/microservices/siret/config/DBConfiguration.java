package com.vphan.microservices.siret.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@Component
@ConfigurationProperties("db")
public class DBConfiguration {
  @NotBlank
  private String fileLocation;
}
