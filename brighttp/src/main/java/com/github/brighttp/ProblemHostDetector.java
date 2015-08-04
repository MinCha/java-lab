package com.github.brighttp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProblemHostDetector {
  private final int NO_PROBLEM = 0;
  private final int PROBLEM = 1;

  private Map<String, Integer> data = new ConcurrentHashMap<String, Integer>();
  private int criteriaTimeInMillis;
  private int criteriaCount;
  private HashBasedMutexProvider mutex = new HashBasedMutexProvider();

  private ProblemHostDetector() {}

  public static ProblemHostDetector criteria(int timeCriteria, int criteriaCount) {
    ProblemHostDetector result = new ProblemHostDetector();
    result.criteriaTimeInMillis = timeCriteria;
    result.criteriaCount = criteriaCount;
    return result;
  }

  public void reportResponseTime(String url, int responseTime) {
    try {
      synchronized (mutex.get(url)) {
        String host = toHost(url);
        Integer errorCount = data.get(host);
        if (errorCount == null) {
          data.put(host, responseTime < criteriaTimeInMillis ? NO_PROBLEM : PROBLEM);
        } else {
          data.put(host, responseTime > criteriaTimeInMillis ? errorCount + PROBLEM : NO_PROBLEM);
        }
      }
    } catch (Exception ignored) {}
  }

  public boolean isProblem(String url) {
    String host;
    try {
      host = toHost(url);
      if (data.containsKey(host) == false) {
        return false;
      }
      return data.get(host) > criteriaCount;
    } catch (MalformedURLException e) {
      return false;
    }
  }

  private String toHost(String url) throws MalformedURLException {
    return new URL(url).getHost();
  }

}
