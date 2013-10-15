package lab.elasticsearch;

import io.searchbox.annotations.JestId;

public class Music {
  @JestId
  private String title;

  public static Music of(String title) {
    Music result = new Music();
    result.setTitle(title);
    return result;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
