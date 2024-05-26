package com.hb.organizationsubscription.adapters.driven.database.mapping;

import com.hb.organizationsubscription.adapters.driven.database.model.OrganizationEntity;
import com.hb.organizationsubscription.domain.Organization;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationEntityMapper {

  OrganizationEntity organizationToOrganizationEntity(Organization organization);

  Organization organizationEntityToOrganization(OrganizationEntity organizationEntity);
}
