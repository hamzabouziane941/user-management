package com.hb.test.authorizationserver.authentication;

import com.hb.test.authorizationserver.AuthorizationServerProperties;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationClient {

  private static final int ONE_SECOND_IN_MS = 1000;
  private static final int BEFORE_EXPIRATION_MS = 60000;

  private final RestTemplate restTemplate;
  private final AuthorizationServerProperties authorizationServerProperties;
  private final ThreadLocal<Token> currentToken = ThreadLocal.withInitial(Token::new);

  public String getAccessToken() {
    Token currentToken = this.currentToken.get();
    if (currentToken.getAccessToken() == null
        || new Date().getTime() > currentToken.getRefreshAfter()) {
      Token newToken = fetchNewToken();
      int durationInMs = newToken.getExpiresIn() * ONE_SECOND_IN_MS;
      String newAccessToken = newToken.getAccessToken();
      currentToken.setAccessToken(newAccessToken);
      currentToken.setRefreshAfter(new Date().getTime() + durationInMs - BEFORE_EXPIRATION_MS);
    }
    return currentToken.getAccessToken();
  }

  private Token fetchNewToken() {
    ResponseEntity<Token> response = restTemplate
        .postForEntity(buildTokenAccessUrl(),
            new HttpEntity<>(buildTokenAccessBody(), buildTokenAccessHeaders()),
            Token.class);

    return response.getBody();
  }

  private String buildTokenAccessUrl() {
    return UriComponentsBuilder.fromHttpUrl(authorizationServerProperties.getBaseUrl())
        .pathSegment("realms", "master", "protocol", "openid-connect", "token").build()
        .toString();
  }

  private static HttpHeaders buildTokenAccessHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    return headers;
  }

  private MultiValueMap<String, String> buildTokenAccessBody() {
    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("grant_type", "client_credentials");
    requestBody.add("client_id", authorizationServerProperties.getClientId());
    requestBody.add("client_secret", authorizationServerProperties.getClientSecret());
    return requestBody;
  }
}
