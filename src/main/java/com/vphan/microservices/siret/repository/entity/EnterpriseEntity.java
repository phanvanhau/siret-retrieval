package com.vphan.microservices.siret.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
  private String creationDate;
  private String tvaNumber;
}
