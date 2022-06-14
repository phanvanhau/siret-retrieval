package com.vphan.microservices.learning.service;

import com.vphan.microservices.learning.client.EnterpriseClient;
import com.vphan.microservices.learning.exception.BusinessException;
import com.vphan.microservices.learning.model.EnterpriseDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
      throw new BusinessException("failed.to.update.enterprises", "", HttpStatus.INTERNAL_SERVER_ERROR);
    } else {
      return response.get();
    }
  }
}
