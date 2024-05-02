package com.hb.test.http;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.ContentType;

@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
public class WorkerRequest {

  @Getter
  private final String urlId;
  @Getter
  private final String path;
  private final HttpMethod method;
  private final Map<String, String> requestParams;
  private final Map<String, String> requestHeaders;
  @Getter
  private final ContentType contentType;
  @Getter
  private final String body;

  public Map<String, String> getRequestParams() {
    return this.requestParams == null ? Collections.emptyMap() : this.requestParams;
  }

  public Map<String, String> getRequestHeaders() {
    return this.requestHeaders == null ? Collections.emptyMap() : this.requestHeaders;
  }

  HttpUriRequestBase createHttpRequestForUri(URI uri) {
    return method.createHttpRequestForUri(uri);
  }

  public enum HttpMethod {
    GET, POST, PUT, DELETE;

    HttpUriRequestBase createHttpRequestForUri(URI uri) {
      return new HttpUriRequestBase(this.name(), uri);
    }
  }
}
