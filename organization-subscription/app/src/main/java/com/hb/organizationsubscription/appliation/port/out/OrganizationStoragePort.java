package com.hb.organizationsubscription.appliation.port.out;

import com.hb.organizationsubscription.domain.Organization;

public interface OrganizationStoragePort {

  Organization store(Organization organization);
}
