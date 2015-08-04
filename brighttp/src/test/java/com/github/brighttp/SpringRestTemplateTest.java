package com.github.brighttp;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class SpringRestTemplateTest {
  @Test
  public void canGetUrlContent() {
    RestTemplate template = new RestTemplate(new BrighttpFactory());

    String result = template.getForObject("http://wave.ivorypen.com", String.class);

    assertThat(result.isEmpty(), is(false));
  }

  @Test
  public void canGetSecureUrlContent() {
    RestTemplate template = new RestTemplate(new BrighttpFactory());

    String result = template.getForObject("https://google.com", String.class);

    assertThat(result.isEmpty(), is(false));
  }
}
