package com.hb.organizationsubscription.appliation.port.in;

import com.hb.organizationsubscription.appliation.exception.OrganizationCreationException;
import com.hb.organizationsubscription.domain.Organization;

public interface CreateOrganizationUseCase {

  Organization createOrganization(Organization organization) throws OrganizationCreationException;
}
