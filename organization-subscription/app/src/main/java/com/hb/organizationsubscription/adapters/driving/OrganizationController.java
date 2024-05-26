package com.hb.organizationsubscription.adapters.driving;

import com.hb.openapi.api.OrganizationsApi;
import com.hb.openapi.model.OrganizationDto;
import com.hb.organizationsubscription.adapters.driving.mapper.OrganizationDtoMapper;
import com.hb.organizationsubscription.appliation.port.in.CreateOrganizationUseCase;
import com.hb.organizationsubscription.domain.Organization;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrganizationController implements OrganizationsApi {

  private final CreateOrganizationUseCase createOrganizationUseCase;

  public ResponseEntity<OrganizationDto> createOrganization(
      @RequestBody OrganizationDto organizationDto
  ) throws Exception {
    OrganizationDtoMapper organizationDtoMapper = Mappers.getMapper(OrganizationDtoMapper.class);
    Organization organization = organizationDtoMapper.organizationDtoToOrganization(
        organizationDto);
    Organization createdOrganization = createOrganizationUseCase.createOrganization(organization);
    return ResponseEntity.ok(
        organizationDtoMapper.organizationToOrganizationDto(createdOrganization));
  }

}
