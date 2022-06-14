package com.vphan.microservices.siret.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class EnterpriseEntity {
  @NotNull
  private String id;
  @NotNull
  private String siret;
  @NotNull
  private String nic;
  @NotNull
  private String fullName;
  private String fullAddress;
  private Long creationDate; // Unix Epoch format
  private String tvaNumber;
}
