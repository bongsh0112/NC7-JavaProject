package bitcamp.myapp.vo;

public class Song {

  public int id;
  public String title;
  public String singer;
  public String album;
  public String genre;
  public int year;
  public boolean like;

  public Song() {
    this.id++;
  }

  public Song(int id) {
    this.id = id;
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
    return like;
  }

  public void setLike(boolean like) {
    this.like = like;
  }
}
