package com.hb.organizationsubscription.appliation;

import static org.mockito.Mockito.verify;

import com.hb.organizationsubscription.appliation.exception.OrganizationCreationException;
import com.hb.organizationsubscription.appliation.port.out.AuthortizationServerOrganizationPort;
import com.hb.organizationsubscription.appliation.port.out.OrganizationStoragePort;
import com.hb.organizationsubscription.domain.Organization;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrganizationApplicationServiceTest {

  @InjectMocks
  private OrganizationApplicationService organizationApplicationService;
  @Mock
  private AuthortizationServerOrganizationPort authortizationServerOrganizationPort;
  @Mock
  private OrganizationStoragePort organizationStoragePort;

  @Test
  void should_create_organization_successfully() throws OrganizationCreationException {
    Organization organization = Organization.builder()
        .login("org1")
        .name("Organization 1")
        .mail("test@organization1.com")
        .build();
    organizationApplicationService.createOrganization(organization);

    verify(organizationStoragePort).store(organization);
    verify(authortizationServerOrganizationPort).create(organization);
  }
}
