package com.hb.organizationsubscription.appliation;

import com.hb.organizationsubscription.appliation.exception.OrganizationCreationException;
import com.hb.organizationsubscription.appliation.port.in.CreateOrganizationUseCase;
import com.hb.organizationsubscription.appliation.port.out.CreateOrganizationPort;
import com.hb.organizationsubscription.domain.Organization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationApplicationService implements CreateOrganizationUseCase {

  private final CreateOrganizationPort createOrganizationPort;

  @Override
  public void createOrganization(Organization organization) throws OrganizationCreationException {

    log.info("The Organization '{}' creation started", organization.getLogin());
    createOrganizationPort.createOrganization(organization);
    log.info("The Organization '{}' was created successfully", organization.getLogin());
  }
}
