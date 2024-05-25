package com.hb.organizationsubscription.adapters.driven.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;

@Table(name = "organization", schema = "public")
@Entity
@Data
public class OrganizationEntity {


  @Id
  private UUID id;

  private String name;
  @Getter
  private String login;

  private String mail;

}
