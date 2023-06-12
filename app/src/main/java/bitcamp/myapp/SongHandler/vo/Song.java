package bitcamp.myapp.SongHandler.vo;

public class Song {

  public int no;
  public String title;
  public String singer;
  public String album;
  public String genre;
  public int year;
  public boolean like;

  public Song(int no, String title, String singer, String album, String genre, int year, boolean like) {
    this.no = no;
    this.title = title;
    this.singer = singer;
    this.album = album;
    this.genre = genre;
    this.year = year;
    this.like = like;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
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
