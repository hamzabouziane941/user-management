package com.hb.test.cucumber.http;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ContentType;

@Slf4j
@RequiredArgsConstructor
public class HttpRequestSteps {

  private static final ContentType CONTENT_TYPE_DEFAULT = ContentType.APPLICATION_JSON;

  private final WorkerClient workerClient;

  private String urlId;
  private String path;
  private final Map<String, String> requestParams = new HashMap<>();
  private final Map<String, String> requestHeaders = new HashMap<>();
  private ContentType contentType = CONTENT_TYPE_DEFAULT;
  private WorkerResponse workerResponse;

  @Before
  public void reset() {
    this.path = "/";
    this.requestParams.clear();
    this.requestHeaders.clear();
    this.contentType = CONTENT_TYPE_DEFAULT;
  }

  @Given("Request/request url is/was {string} url")
  public void requestUrlCorrespondingTo(String apiUrlKey) {
    this.urlId = apiUrlKey;
  }

  @And("Request/request path is/was {string}")
  public void requestPath(String path) {
    this.path = path;
  }

  @When("Post/post/Posted/posted with body")
  public void postWithPayload(String body) throws IOException {
    WorkerRequest httpRequest = WorkerRequest.builder()
        .urlId(urlId)
        .path(path)
        .method(WorkerRequest.HttpMethod.POST)
        .requestHeaders(requestHeaders)
        .requestParams(requestParams)
        .contentType(contentType)
        .body(body)
        .build();
    workerResponse = workerClient.sendRequest(httpRequest);
  }

  @Then("Response/response status is/was {int}")
  public void responseStatusIs(int status) {
    assertThat(workerResponse.status()).isEqualTo(status);
  }
}
