package com.vphan.microservices.siret.client;

import com.vphan.microservices.siret.config.EnterpriseConfiguration;
import com.vphan.microservices.siret.model.EnterpriseDetail;
import com.vphan.microservices.siret.util.RestClientUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class EnterpriseClient {

  private final EnterpriseConfiguration enterpriseConfiguration;

  private static final String GET_ENTERPRISE_BY_SIRET = "/api/sirene/v3/etablissements/{siret}";

  public Optional<EnterpriseDetail> getEnterpriseDetails(String siret) {
    try {
      return Optional.ofNullable(
        new RestTemplate().exchange(
          enterpriseConfiguration.getBaseUrl() +  GET_ENTERPRISE_BY_SIRET,
          HttpMethod.GET,
          null,
          EnterpriseDetail.class,
          siret
        ).getBody()
      );
    } catch (Exception e) {
      RestClientUtil.logWebClientError(log, GET_ENTERPRISE_BY_SIRET, e);
      return Optional.empty();
    }
  }
}
