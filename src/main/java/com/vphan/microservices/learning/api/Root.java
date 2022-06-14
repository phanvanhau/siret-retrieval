package com.vphan.microservices.learning.api;

import com.vphan.microservices.learning.model.EnterpriseDetail;
import com.vphan.microservices.learning.service.EnterpriseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
public class Root {

  private final EnterpriseService enterpriseService;

  @GetMapping("/toto")
  public ResponseEntity<EnterpriseDetail> getToto() {
    log.info("[TOTO] - getToto");
    return ResponseEntity.ok(this.enterpriseService.getEnterpriseDetail("97080195700014dd"));
  }

}
