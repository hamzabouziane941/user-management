package com.hb.test.authorizationserver;

import com.hb.test.authorizationserver.authentication.AuthenticationClient;
import io.cucumber.java.en.And;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Slf4j
public class AuthorizationServerSteps {

  private final AuthorizationServerProperties authorizationServerProperties;
  private final AuthenticationClient authenticationClient;
  private final RestTemplate restTemplate;

  @And("Organization {string} isn't created")
  public void requestPath(String organizationName) {
    if (AuthorizationServerType.Keycloak.equals(authorizationServerProperties.getType())) {
      if (isOrganizationCreated(organizationName)) {
        deleteOrganization(organizationName);
      }
    }
  }

  private boolean isOrganizationCreated(String organizationName) {
    try {
      String accessToken = authenticationClient.getAccessToken();
      HttpEntity<Void> requestEntity = new HttpEntity<>(buildHeaders(accessToken));
      restTemplate.exchange(buildRealmResourceUrl(organizationName), HttpMethod.GET, requestEntity,
          Void.class);
      return true;
    } catch (Exception exception) {
      if (isOrganizationNotFound(exception)) {
        log.info("Organization {} isn't created yet", organizationName);
        return false;
      } else {
        throw new IllegalStateException("Organization search failed", exception);
      }
    }
  }

  private void deleteOrganization(String organizationName) {
    try {
      String accessToken = authenticationClient.getAccessToken();
      HttpEntity<Void> requestEntity = new HttpEntity<>(buildHeaders(accessToken));
      restTemplate.exchange(buildRealmResourceUrl(organizationName), HttpMethod.DELETE,
          requestEntity,
          Void.class);
    } catch (Exception exception) {
      throw new IllegalStateException("Organization deletion failed", exception);
    }
  }

  private String buildRealmResourceUrl(String organizationName) {
    return UriComponentsBuilder.fromHttpUrl(authorizationServerProperties.getBaseUrl())
        .pathSegment("admin", "realms", organizationName).build().toString();
  }

  private HttpHeaders buildHeaders(String accessToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer ".concat(accessToken));
    return headers;
  }

  private boolean isOrganizationNotFound(Exception exception) {
    return exception instanceof HttpClientErrorException && HttpStatus.NOT_FOUND.equals((
        (HttpClientErrorException) exception).getStatusCode());
  }

}
