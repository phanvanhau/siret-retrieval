package com.vphan.microservices.siret.service;

import com.vphan.microservices.siret.client.EnterpriseClient;
import com.vphan.microservices.siret.repository.EnterpriseRepository;
import com.vphan.microservices.siret.repository.SiretRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnterpriseService {
  private final EnterpriseClient enterpriseClient;
  private final SiretRepository siretRepository;
  private final EnterpriseRepository enterpriseRepository;

  public void updateEnterprisesDetail() {
    List<String> sirets = siretRepository.all();
    log.info("Number of stored enterprise: {}", sirets.size());
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (String s : sirets) {
      try {
        executorService.submit(new EnterpriseUpdateWorker(s, enterpriseClient, enterpriseRepository));
      } catch (RejectedExecutionException ex) {
        log.error("Could not update enterprise detail for siret={}", s);
      }
    }
  }
}
