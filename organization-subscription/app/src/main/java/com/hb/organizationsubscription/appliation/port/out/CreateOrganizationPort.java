package com.hb.organizationsubscription.appliation.port.out;

import com.hb.organizationsubscription.appliation.exception.OrganizationCreationException;
import com.hb.organizationsubscription.domain.Organization;

public interface CreateOrganizationPort {

  void createOrganization(Organization organization) throws OrganizationCreationException;
}
