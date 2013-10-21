package lab.elasticsearch;

import io.searchbox.annotations.JestId;

import java.util.List;

import lab.BaseObject;

public class User extends BaseObject {
  @JestId
  private String id;
  private String name;
  private List<MusicPlayEvent> plays;
  private List<String> follows;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<MusicPlayEvent> getPlays() {
    return plays;
  }

  public void setPlays(List<MusicPlayEvent> plays) {
    this.plays = plays;
  }

  public List<String> getFollows() {
    return follows;
  }

  public void setFollows(List<String> follows) {
    this.follows = follows;
  }
}
