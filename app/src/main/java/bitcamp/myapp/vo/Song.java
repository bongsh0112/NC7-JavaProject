package bitcamp.myapp.vo;

import java.io.Serializable;

public class Song implements Serializable, JsonObject, AutoIncrement {
  
  private static int songId = 1;

  private final int id;
  private String title;
  private String singer;
  private String album;
  private String genre = "";
  private int year;
  private boolean like;

  public Song() {
    this.id = songId++;
  }
  
  public Song(int id) {
    this.id = id;
  }
  
  public static Song fromJson(String json) {
    String[] values = json.split(",");
    
    Song song = new Song(Integer.parseInt(values[0]));
    song.setTitle(values[1]);
    song.setSinger(values[2]);
    song.setAlbum(values[3]);
    song.setYear(Integer.parseInt(values[4]));
    song.setGenre(values[5]);
    if (values[6].equals("like")) {
      song.setLike(true);
    } else {
      song.setLike(false);
    }
    
    if (Song.songId <= song.getId()) {
      Song.songId = song.getId() + 1;
    }
    
    return song;
  }
  
  @Override
  public String toJsonString() {
    String saveData = "";
    if (this.isLike()) {
      saveData = String.format("%d,%s,%s,%s,%d,%s,%s\n",
              this.getId(),
              this.getTitle(),
              this.getSinger(),
              this.getAlbum(),
              this.getYear(),
              this.getGenre(),
              "like"
              );
    } else {
      String.format("%d,%s,%s,%s,%d,%s,%s\n",
              this.getId(),
              this.getTitle(),
              this.getSinger(),
              this.getAlbum(),
              this.getYear(),
              this.getGenre(),
              "unlike"
      );
    }
    return saveData;
  }
  
  @Override
  public void updateKey() {
    if (Song.songId <= this.id) { // Song 클래스 id가 어떤 Song 객체의 id보다 작거나 같다면 현재 객체의 songId = this.id + 1
      Song.songId = this.id + 1;
    }
  }
  
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    Song s = (Song) obj;
    if (this.getId() != s.getId()) {
      return false;
    }
    return true;
  }
  
  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSinger() {
    return singer;
  }

  public void setSinger(String singer) {
    this.singer = singer;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public boolean isLike() {
    return this.like;
  }

  public void setLike(boolean like) {
    this.like = like;
  }
}
