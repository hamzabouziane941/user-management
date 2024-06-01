package com.hb.organizationsubscription.adapters.driving.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

  private UUID id;

  private String name;
  private String login;

  private String mail;
}
