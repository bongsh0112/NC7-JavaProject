package bitcamp.myapp.vo;

public class ReviewBoard {

  private static int boardNo = 1;

//  번호 노래 감상평 가수 비밀번호 조회수 쓴날짜
  private int no;
  private String song;
  private String content;
  private String singer;
  private String password;
  private int viewCount;
  private long createdDate;

  public ReviewBoard() {
    this.no = boardNo++;
    this.viewCount = 0;
    this.createdDate = System.currentTimeMillis();
  }

  public ReviewBoard(int no) {
    this.no = boardNo;
  }

  public static int getBoardNo() {
    return boardNo;
  }

  public static void setBoardNo(int boardNo) {
    ReviewBoard.boardNo = boardNo;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getSong() {
    return song;
  }

  public void setSong(String song) {
    this.song = song;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSinger() {
    return singer;
  }

  public void setSinger(String singer) {
    this.singer = singer;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(long createdDate) {
    this.createdDate = createdDate;
  }
}
