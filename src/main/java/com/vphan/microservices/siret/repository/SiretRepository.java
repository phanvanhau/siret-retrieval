package com.vphan.microservices.siret.repository;

import com.vphan.microservices.siret.model.EnterpriseDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Slf4j
@Repository
public class SiretRepository extends AbstractFileRepository<String>{
  private static final String SIRET_TABLE_SOURCE = "siret_table.csv";

  @PostConstruct
  public void loadDB() {
    super.loadDB();
  }

  @Override
  public String getTableName() {
    return SIRET_TABLE_SOURCE;
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
