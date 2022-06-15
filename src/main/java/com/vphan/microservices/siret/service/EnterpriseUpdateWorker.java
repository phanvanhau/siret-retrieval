package com.vphan.microservices.siret.service;

import com.vphan.microservices.siret.client.EnterpriseClient;
import com.vphan.microservices.siret.model.EnterpriseDetail;
import com.vphan.microservices.siret.repository.EnterpriseRepository;
import com.vphan.microservices.siret.repository.mapper.EnterpriseDTOMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.Callable;

@Slf4j
public class EnterpriseUpdateWorker implements Callable<Optional<EnterpriseDetail>> {
  private String siret;
  private EnterpriseClient enterpriseClient;
  private EnterpriseRepository enterpriseRepository;

  public EnterpriseUpdateWorker(String siret, EnterpriseClient enterpriseClient, EnterpriseRepository enterpriseRepository) {
    this.siret = siret;
    this.enterpriseClient = enterpriseClient;
    this.enterpriseRepository = enterpriseRepository;
  }

  @Override
  public Optional<EnterpriseDetail> call() throws Exception {
    Optional<EnterpriseDetail> response = enterpriseClient.getEnterpriseDetails(siret);
    if (response.isEmpty() || response.get().getSociety() == null) {
      log.error("Could not GET enterprise detail for siret={}", siret);
      return Optional.empty();
    }
    this.enterpriseRepository.save(EnterpriseDTOMapper.MAPPER.apiResponseToEnterpriseEntity(response.get().getSociety()));
    return response;
  }
}
