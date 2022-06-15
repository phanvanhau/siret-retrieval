package com.vphan.microservices.siret.api;

import com.vphan.microservices.siret.model.EnterpriseDetail;
import com.vphan.microservices.siret.service.EnterpriseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
public class EnterpriseApi {

  private final EnterpriseService enterpriseService;

  @PostMapping("/enterprise/batchUpdate")
  public ResponseEntity<Void> updateEnterprise() {
    log.info("[Enterprise API] - updateEnterprise");
    this.enterpriseService.updateEnterprisesDetail();
    return ResponseEntity.ok().build();
  }

}
