package com.hb.test.cucumber.http;

public record WorkerResponse(int status, String body) {

  public WorkerResponse(int status, String body) {
    this.status = status;
    this.body = body;
  }

  public int status() {
    return this.status;
  }

  public String body() {
    return this.body;
  }
}
