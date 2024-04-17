package com.hb.organizationsubscription.appliation;

import com.hb.organizationsubscription.appliation.exception.OrganizationCreationException;
import com.hb.organizationsubscription.appliation.port.in.CreateOrganizationUseCase;
import com.hb.organizationsubscription.appliation.port.out.CreateOrganizationPort;
import com.hb.organizationsubscription.domain.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationApplicationService implements CreateOrganizationUseCase {

  private final CreateOrganizationPort createOrganizationPort;

  @Override
  public void createOrganization(Organization organization) throws OrganizationCreationException {

      createOrganizationPort.createOrganization(organization);
  }
}
