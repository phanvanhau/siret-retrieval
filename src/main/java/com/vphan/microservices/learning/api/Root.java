package com.vphan.microservices.learning.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//@Slf4j
@RestController
public class Root {

  Logger log = LoggerFactory.getLogger(Root.class);

  @GetMapping("/toto")
  public ResponseEntity<String> getToto() {
    log.info("Request to root");
    log.debug("This request must be forbidden");
    return ResponseEntity.ok("This is Spring Boot root API!");
  }

}
