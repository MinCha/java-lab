package refactoring.day1.practice06.DuplicateCode.extractMethod;

import java.util.Properties;

public class Props {
  public void getTimes(Properties props) throws Exception {
    String valueString = null;
    int value = 0;

    valueString = property(props, "interval");
    assertNotNull(valueString, "monitor interval");
    value = Integer.parseInt(valueString);

    if (value <= 0) {
      throw new Exception("monitor interval > 0");
    }
    int checkInterval = value;

    valueString = property(props, "duration");
    assertNotNull(valueString, "duration");

    value = Integer.parseInt(valueString);
    if (value <= 0) {
      throw new Exception("duration > 0");
    }
    if ((value % checkInterval) != 0) {
      throw new Exception("duration % checkInterval");
    }
    int monitorTime = value;

    valueString = property(props, "departure");
    assertNotNull(valueString, "departure offset");

    value = Integer.parseInt(valueString);
    if (value <= 0) {
      throw new Exception("departure > 0");
    }
    if ((value % checkInterval) != 0) {
      throw new Exception("departure % checkInterval?");
    }
    int departureOffset = value;
  }

  private int parseIntIfPosstive(String valueString, int checkInterval, String name) throws Exception {
    int result = Integer.parseInt(valueString);

    if (result <= 0) {
      throw new Exception("departure > 0");
    }
    if ((result % checkInterval) != 0) {
      throw new Exception("departure % checkInterval?");
    }

    return result;
  }

  private String property(Properties props, String key) {
    return props.getProperty(key);
  }

  private void assertNotNull(Object object, String errorMessage) throws Exception {
    if (object == null) {
      throw new Exception(errorMessage);
    }
  }
}
