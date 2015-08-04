package com.github.brighttp;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProblemHostDetectorTest {
  @Test
  public void shouldKnowProlbemWhenUrlSeemsLikeBroken() {
    final int timeCriteria = 3000;
    final int countCriteria = 20;
    ProblemHostDetector sut = ProblemHostDetector.criteria(timeCriteria, countCriteria);

    final String url = "http://wave.ivorypen.com";
    final int slow = 3000 * 2;
    for (int i = 0; i < countCriteria + 1; i++) {
      sut.reportResponseTime(url, slow);
    }

    assertThat(sut.isProblem(url), is(true));
  }

  @Test
  public void shouldNotJudgeAsProblemWhenThereIsSuccessfulRequest() {
    final int timeCriteria = 3000;
    final int countCriteria = 20;
    ProblemHostDetector sut = ProblemHostDetector.criteria(timeCriteria, countCriteria);

    final String url = "http://wave.ivorypen.com";
    final int fast = 50;
    final int slow = 3000 * 2;
    for (int i = 0; i < countCriteria + 1; i++) {
      sut.reportResponseTime(url, i % 2 == 0 ? fast : slow);
    }

    assertThat(sut.isProblem(url), is(false));
  }
}
