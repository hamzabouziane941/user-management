package com.hb.organizationsubscription.adapters.driving.mapper;

import com.hb.organizationsubscription.adapters.driving.dto.OrganizationDto;
import com.hb.organizationsubscription.domain.Organization;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface OrganizationDtoMapper {

  Organization organizationDtoToOrganization(OrganizationDto organizationDto);
}
