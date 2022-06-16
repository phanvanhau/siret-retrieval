package com.vphan.microservices.siret.repository;

import com.vphan.microservices.siret.repository.entity.EnterpriseEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnterpriseRepositoryTest {
  private EnterpriseRepository enterpriseRepository;

  @BeforeEach
  public void setup() {
    this.enterpriseRepository = new EnterpriseRepository(null);
  }
  @Test
  public void deserializeEntity() {
    EnterpriseEntity entity = enterpriseRepository.deserializeEntity("1;97080195700014;00014;LE DUB;3 Rue Saint-François de Paule 06300 Nice;2021-03-08;FR89970801957");
    Assertions.assertEquals(
      new EnterpriseEntity("1", "97080195700014", "00014", "LE DUB", "3 Rue Saint-François de Paule 06300 Nice", "2021-03-08", "FR89970801957"),
      entity);
  }

  @Test
  public void serializeEntity() {
    EnterpriseEntity entity = new EnterpriseEntity("1", "97080195700014", "00014", "LE DUB", "3 Rue Saint-François de Paule 06300 Nice", "2021-03-08", "FR89970801957");
    Assertions.assertEquals("1;97080195700014;00014;LE DUB;3 Rue Saint-François de Paule 06300 Nice;2021-03-08;FR89970801957", enterpriseRepository.serializeEntity(entity));
  }

  @Test
  public void isEntityEqual() {
    Assertions.assertFalse(enterpriseRepository.isEqual(null, null));
    Assertions.assertFalse(enterpriseRepository.isEqual(new EnterpriseEntity(), new EnterpriseEntity()));
    Assertions.assertTrue(enterpriseRepository.isEqual(
      new EnterpriseEntity("1", "97080195700014", "00014", "LE DUB", "3 Rue Saint-François de Paule 06300 Nice", "2021-03-08", "FR89970801957"),
      new EnterpriseEntity("1", "97080195700014", "00014", "LE DUB", "3 Rue Saint-François de Paule 06300 Nice", "2021-03-08", "FR89970801957")
    ));
  }
}
