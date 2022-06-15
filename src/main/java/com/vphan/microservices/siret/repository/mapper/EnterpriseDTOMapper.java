package com.vphan.microservices.siret.repository.mapper;

import com.vphan.microservices.siret.model.EnterpriseDetail;
import com.vphan.microservices.siret.model.Society;
import com.vphan.microservices.siret.repository.entity.EnterpriseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnterpriseDTOMapper {

  EnterpriseDTOMapper MAPPER = Mappers.getMapper(EnterpriseDTOMapper.class);

  @Mapping(target = "fullName", source = "society.uniteLegale.name")
  @Mapping(target = "tvaNumber", source = "society.uniteLegale.tvaNumber")
  EnterpriseEntity apiResponseToEnterpriseEntity(Society society);

}
