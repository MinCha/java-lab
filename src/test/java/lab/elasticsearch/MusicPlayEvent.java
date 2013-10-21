package lab.elasticsearch;

import io.searchbox.annotations.JestId;

import java.util.Date;

import lab.BaseObject;

public class MusicPlayEvent extends BaseObject {
  @JestId
  private String musicId;
  private Date lastPlayed;
  private int count;

  public String getMusicId() {
    return musicId;
  }

  public void setMusicId(String musicId) {
    this.musicId = musicId;
  }

  public Date getLastPlayed() {
    return lastPlayed;
  }

  public void setLastPlayed(Date lastPlayed) {
    this.lastPlayed = lastPlayed;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
