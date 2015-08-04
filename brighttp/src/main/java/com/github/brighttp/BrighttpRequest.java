package com.github.brighttp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

public class BrighttpRequest implements ClientHttpRequest {
  private ClientHttpRequest request;

  public BrighttpRequest(ClientHttpRequest request) {
    this.request = request;
  }

  public HttpMethod getMethod() {
    return request.getMethod();
  }

  public URI getURI() {
    return request.getURI();
  }

  public HttpHeaders getHeaders() {
    return request.getHeaders();
  }

  public OutputStream getBody() throws IOException {
    return request.getBody();
  }

  public ClientHttpResponse execute() throws IOException {
    return request.execute();
  }

}
