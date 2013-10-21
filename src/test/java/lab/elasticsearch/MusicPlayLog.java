package lab.elasticsearch;

import lab.BaseObject;

public class MusicPlayLog extends BaseObject {
  private String userId;
  private String musicId;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getMusicId() {
    return musicId;
  }

  public void setMusicId(String musicId) {
    this.musicId = musicId;
  }
}
