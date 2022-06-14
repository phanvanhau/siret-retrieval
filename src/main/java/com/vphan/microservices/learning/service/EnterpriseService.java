package com.vphan.microservices.learning.service;

import com.vphan.microservices.learning.client.EnterpriseClient;
import com.vphan.microservices.learning.model.EnterpriseDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnterpriseService {
  private final EnterpriseClient enterpriseClient;

  public EnterpriseDetail getEnterpriseDetail(String siret) {
    Optional<EnterpriseDetail> response = this.enterpriseClient.getEnterpriseDetails(siret);
    if (response.isEmpty()) {
      log.error("Could not GET enterprise detail");
      return null;
    } else {
      return response.get();
    }
  }
}
