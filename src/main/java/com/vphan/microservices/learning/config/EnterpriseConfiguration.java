package com.vphan.microservices.learning.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@Component
@ConfigurationProperties("enterprise")
public class EnterpriseConfiguration {
  @NotBlank
  private String baseUrl;
}
