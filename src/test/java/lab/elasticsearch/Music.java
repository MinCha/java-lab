package lab.elasticsearch;

import lab.BaseObject;

public class Music extends BaseObject {
  private Integer id;
  private String title;

  public static Music of(Integer id, String title) {
    Music result = new Music();
    result.setId(id);
    result.setTitle(title);
    return result;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
