package com.hb.organizationsubscription.adapters.driving.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class OrganizationDto {

  @NotBlank
  private String name;
  @Getter
  @NotBlank
  private String login;
  private String mail;
}
