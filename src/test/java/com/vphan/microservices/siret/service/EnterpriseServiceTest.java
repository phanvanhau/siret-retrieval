package com.vphan.microservices.siret.service;

import com.vphan.microservices.siret.client.EnterpriseClient;
import com.vphan.microservices.siret.model.EnterpriseDetail;
import com.vphan.microservices.siret.model.Society;
import com.vphan.microservices.siret.repository.EnterpriseRepository;
import com.vphan.microservices.siret.repository.SiretRepository;
import com.vphan.microservices.siret.repository.entity.EnterpriseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EnterpriseServiceTest {
  private EnterpriseService enterpriseService;
  private EnterpriseClient enterpriseClient;
  private SiretRepository siretRepository;
  private EnterpriseRepository enterpriseRepository;
  @BeforeEach
  public void setup() {
    this.enterpriseClient = mock(EnterpriseClient.class);
    this.siretRepository = mock(SiretRepository.class);
    this.enterpriseRepository = mock(EnterpriseRepository.class);
    this.enterpriseService = new EnterpriseService(enterpriseClient, siretRepository, enterpriseRepository);
  }
  @Test
  public void updateEnterprisesDetail() {
    List<String> mockedSirets = List.of("1", "2", "3");

    // PREPARATION
    when(siretRepository.all()).thenReturn(mockedSirets);
    doAnswer(invocation -> {
      String siret = invocation.getArgument(0);
      if ("1".equals(siret) || "3".equals(siret)) {
        return Optional.of(new EnterpriseDetail(new Society()));
      } else {
        return Optional.empty();
      }
    }).when(enterpriseClient).getEnterpriseDetails(anyString());

    // SCENARIO
    this.enterpriseService.updateEnterprisesDetail();

    // TEST
    verify(enterpriseClient, times(3)).getEnterpriseDetails(anyString());
    verify(enterpriseRepository, times(2)).save(any(EnterpriseEntity.class));
  }
}
