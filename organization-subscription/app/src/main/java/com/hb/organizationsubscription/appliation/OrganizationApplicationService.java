package com.hb.organizationsubscription.appliation;

import com.hb.organizationsubscription.appliation.exception.OrganizationCreationException;
import com.hb.organizationsubscription.appliation.port.in.CreateOrganizationUseCase;
import com.hb.organizationsubscription.appliation.port.out.AuthortizationServerOrganizationPort;
import com.hb.organizationsubscription.appliation.port.out.OrganizationStoragePort;
import com.hb.organizationsubscription.domain.Organization;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationApplicationService implements CreateOrganizationUseCase {

  private final AuthortizationServerOrganizationPort authortizationServerOrganizationPort;
  private final OrganizationStoragePort organizationStoragePort;

  @Override
  @Transactional
  public Organization createOrganization(Organization organization)
      throws OrganizationCreationException {

    log.info("The Organization '{}' creation started", organization.getLogin());
    Organization createdOrganization = organizationStoragePort.store(organization);
    authortizationServerOrganizationPort.create(organization);
    log.info("The Organization '{}' was created successfully", organization.getLogin());
    return createdOrganization;
  }
}
