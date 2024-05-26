package com.hb.organizationsubscription.adapters.driven.database;

import com.hb.organizationsubscription.adapters.driven.database.mapping.OrganizationEntityMapper;
import com.hb.organizationsubscription.adapters.driven.database.model.OrganizationEntity;
import com.hb.organizationsubscription.appliation.port.out.OrganizationStoragePort;
import com.hb.organizationsubscription.domain.Organization;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationDatabaseAdapter implements OrganizationStoragePort {

  private final OrganizationRepository organizationRepository;

  @Override
  public Organization store(Organization organization) {
    OrganizationEntityMapper organizationEntityMapper = Mappers.getMapper(
        OrganizationEntityMapper.class);
    OrganizationEntity organizationEntity = organizationRepository.save(
        organizationEntityMapper.organizationToOrganizationEntity(organization));
    return organizationEntityMapper.organizationEntityToOrganization(organizationEntity);
  }
}
