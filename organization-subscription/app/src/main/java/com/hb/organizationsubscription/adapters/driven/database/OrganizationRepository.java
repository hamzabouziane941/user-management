package com.hb.organizationsubscription.adapters.driven.database;

import com.hb.organizationsubscription.adapters.driven.database.model.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

}
