package com.hb.test.cucumber.http;

import static java.util.Objects.isNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkerClient {

  private final CloseableHttpClient httpClient;
  private final Map<String, String> urls;

  public WorkerResponse sendRequest(WorkerRequest workerRequest) throws IOException {
    URI uri = mapToUri(workerRequest);
    HttpUriRequestBase request = workerRequest.createHttpRequestForUri(uri);
    if (workerRequest.getBody() != null) {
      request.setEntity(new StringEntity(workerRequest.getBody(), workerRequest.getContentType()));
    }
    workerRequest.getRequestHeaders().forEach(request::setHeader);
    return this.httpClient.execute(request, this::handleResponse);
  }

  private URI mapToUri(WorkerRequest workerRequest) {
    String url = urls.get(workerRequest.getUrlId());
    try {
      URIBuilder uriBuilder = new URIBuilder(url);
      List<String> pathSegments = new ArrayList<>();
      pathSegments.addAll(uriBuilder.getPathSegments());
      pathSegments.addAll((new URIBuilder(workerRequest.getPath())).getPathSegments());
      uriBuilder.setPathSegments(pathSegments);
      uriBuilder.addParameters(mapToNameValuePair(workerRequest));
      return uriBuilder.build();
    } catch (URISyntaxException exception) {
      throw new IllegalArgumentException(String.format("%s isn't a valid url", url), exception);
    }
  }

  private WorkerResponse handleResponse(HttpResponse response) throws IOException {
    try {
      ClassicHttpResponse classicResponse = (ClassicHttpResponse) response;
      String body = isNull(classicResponse.getEntity()) ? StringUtils.EMPTY
          : EntityUtils.toString(classicResponse.getEntity());
      return new WorkerResponse(response.getCode(), body);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  private List<NameValuePair> mapToNameValuePair(WorkerRequest workerRequest) {
    return workerRequest.getRequestParams().entrySet()
        .stream().map(
            (requestParamEntrySet) -> (NameValuePair) new BasicNameValuePair(
                requestParamEntrySet.getKey(),
                requestParamEntrySet.getValue())).toList();
  }
}
