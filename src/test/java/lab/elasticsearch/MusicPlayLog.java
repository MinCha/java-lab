package lab.elasticsearch;

import lab.BaseObject;

public class MusicPlayLog extends BaseObject {
  private int userId;
  private int musicId;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getMusicId() {
    return musicId;
  }

  public void setMusicId(int musicId) {
    this.musicId = musicId;
  }
}
