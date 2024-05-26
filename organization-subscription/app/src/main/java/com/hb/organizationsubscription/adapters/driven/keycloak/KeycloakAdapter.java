package com.hb.organizationsubscription.adapters.driven.keycloak;

import com.hb.organizationsubscription.adapters.driven.keycloak.authentication.AuthenticationClient;
import com.hb.organizationsubscription.appliation.exception.OrganizationCreationException;
import com.hb.organizationsubscription.appliation.port.out.AuthortizationServerOrganizationPort;
import com.hb.organizationsubscription.domain.Organization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Slf4j
public class KeycloakAdapter implements AuthortizationServerOrganizationPort {

  @Value("${keycloak.base-url}")
  private String keycloakBaseUrl;

  private final RestTemplate restTemplate;
  private final AuthenticationClient authenticationClient;

  @Override
  public void create(Organization organization) throws OrganizationCreationException {
    String accessToken = authenticationClient.getAccessToken();
    try {
      restTemplate.postForEntity(buildRealmCreationUrl(),
          new HttpEntity<>(buildRealmCreationBody(
              organization), buildHeaders(accessToken)), Void.class);
    } catch (Exception e) {
      throw new OrganizationCreationException("Realm creation failed", e);
    }

  }

  private String buildRealmCreationUrl() {
    return UriComponentsBuilder.fromHttpUrl(keycloakBaseUrl)
        .pathSegment("admin", "realms").build().toString();
  }

  private RealmCreation buildRealmCreationBody(Organization organization) {
    return new RealmCreation(organization.getLogin());
  }

  private HttpHeaders buildHeaders(String accessToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer ".concat(accessToken));
    return headers;
  }

}
