package com.vphan.microservices.siret.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Society {
  private String id;
  private String siren;
  private String siret;
  private String nic;
  @JsonProperty("date_debut")
  private String creationDate;
  @JsonProperty("geo_adresse")
  private String geoAddress;
  @JsonProperty("unite_legale")
  private LegalUnit uniteLegale;

  public String getFullAddress() {
    return this.geoAddress;
  }
}
