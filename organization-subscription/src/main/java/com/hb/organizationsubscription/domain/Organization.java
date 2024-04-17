package com.hb.organizationsubscription.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
public class Organization {

  private String name;
  @Getter
  private String login;
  private String mail;

}
