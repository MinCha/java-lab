package com.github.brighttp;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class BrighttpFactory implements ClientHttpRequestFactory {
  private SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

  public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
    return new BrighttpRequest(factory.createRequest(uri, httpMethod));
  }
}
