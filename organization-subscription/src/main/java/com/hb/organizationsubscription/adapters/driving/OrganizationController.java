package com.hb.organizationsubscription.adapters.driving;

import com.hb.organizationsubscription.adapters.driving.dto.OrganizationDto;
import com.hb.organizationsubscription.adapters.driving.mapper.OrganizationDtoMapper;
import com.hb.organizationsubscription.appliation.exception.OrganizationCreationException;
import com.hb.organizationsubscription.appliation.port.in.CreateOrganizationUseCase;
import com.hb.organizationsubscription.domain.Organization;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizations")
@RequiredArgsConstructor
public class OrganizationController {

  private final CreateOrganizationUseCase createOrganizationUseCase;
  private final OrganizationDtoMapper organizationDtoMapper;

  @PostMapping
  void createOrganization(@RequestBody @Valid OrganizationDto organizationDto)
      throws OrganizationCreationException {
    Organization organization = organizationDtoMapper.organizationDtoToOrganization(
        organizationDto);
    createOrganizationUseCase.createOrganization(organization);
  }
}
