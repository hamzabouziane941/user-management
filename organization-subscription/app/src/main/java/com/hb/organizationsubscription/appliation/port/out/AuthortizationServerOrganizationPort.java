package com.hb.organizationsubscription.appliation.port.out;

import com.hb.organizationsubscription.appliation.exception.OrganizationCreationException;
import com.hb.organizationsubscription.domain.Organization;

public interface AuthortizationServerOrganizationPort {

  void create(Organization organization) throws OrganizationCreationException;
}
