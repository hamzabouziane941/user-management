package com.hb.organizationsubscription.domain;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Organization {

  private UUID id;

  private String name;
  private String login;

  private String mail;

}
