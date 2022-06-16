package com.vphan.microservices.siret.repository;

import com.vphan.microservices.siret.config.DBConfiguration;
import com.vphan.microservices.siret.model.EnterpriseDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Slf4j
@AllArgsConstructor
@Repository
public class SiretRepository extends AbstractFileRepository<String>{
  private static final String SIRET_TABLE_SOURCE = "siret_table.csv";

  private final DBConfiguration dbConfiguration;

  @PostConstruct
  public void loadDB() {
    super.loadDB(dbConfiguration.getFileLocation() +  SIRET_TABLE_SOURCE);
  }

  @Override
  public String deserializeEntity(String line) {
    return line;
  }

  @Override
  public String serializeEntity(String entity) {
    return entity;
  }

  @Override
  public boolean isEqual(String entity1, String entity2) {
    return entity1 != null && entity1.equals(entity2);
  }

}
