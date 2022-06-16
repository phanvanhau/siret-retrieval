package com.vphan.microservices.siret.repository;

import com.vphan.microservices.siret.config.DBConfiguration;
import com.vphan.microservices.siret.repository.entity.EnterpriseEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Slf4j
@AllArgsConstructor
@Repository
public class EnterpriseRepository extends AbstractFileRepository<EnterpriseEntity>{
  private static final String ENTERPRISE_TABLE_SOURCE = "enterprise_table.csv";

  private final DBConfiguration dbConfiguration;

  @PostConstruct
  public void loadDB() {
    super.loadDB(dbConfiguration.getFileLocation() + ENTERPRISE_TABLE_SOURCE);
  }

  @Override
  public EnterpriseEntity deserializeEntity(String line) {
    String[] columns = line.split(COLUMN_SEPARATOR);
    if (columns.length != 7) {
      return null;
    }
    try {
      return new EnterpriseEntity(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6]);
    } catch (Exception e) {
      return null;
    }

  }

  @Override
  public String serializeEntity(EnterpriseEntity entity) {
    return String.join(COLUMN_SEPARATOR, new String[] {entity.getId(), entity.getSiret(), entity.getNic(),entity.getFullName(), entity.getFullAddress(),entity.getCreationDate(), entity.getTvaNumber()});
  }

  @Override
  public boolean isEqual(EnterpriseEntity entity1, EnterpriseEntity entity2) {
    return entity1 != null && entity2 != null && entity1.getId() != null && entity1.getId().equals(entity2.getId());
  }

}
