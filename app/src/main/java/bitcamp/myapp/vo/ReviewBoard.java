package bitcamp.myapp.vo;

import java.io.Serializable;

public class ReviewBoard implements Serializable, JsonObject, AutoIncrement {

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
    this.no = no;
  }

  public static int getBoardNo() {
    return boardNo;
  }
  
  public static ReviewBoard fromJson(String json) {
    String[] values = json.split(",");
    
    ReviewBoard board = new ReviewBoard(Integer.parseInt(values[0]));
    board.setSong(values[1]);
    board.setContent(values[2]);
    board.setSinger(values[3]);
    board.setPassword(values[4]);
    board.setViewCount(Integer.parseInt(values[5]));
    board.setCreatedDate(Long.parseLong(values[6]));
    
    if (ReviewBoard.boardNo <= board.getNo()) {
      ReviewBoard.boardNo = board.getNo() + 1;
    }
    
    return board;
  }
  
  @Override
  public String toJsonString() {
    return String.format("%d,%s,%s,%s,%s,%d,%d\n",
            this.getNo(),
            this.getSong(),
            this.getContent(),
            this.getSinger(),
            this.getPassword(),
            this.getViewCount(),
            this.getCreatedDate()
    );
  }
  
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    ReviewBoard b = (ReviewBoard) obj;
    if (this.getNo() != b.getNo()) {
      return false;
    }
    return true;
  }
  
  @Override
  public void updateKey() {
    if (ReviewBoard.boardNo <= this.no) { // ReviewBoard 클래스 boardNo가 어떤 ReviewBoard 객체의 no보다 작거나 같다면 현재 객체의 boardNo = this.no + 1
      ReviewBoard.boardNo = this.no + 1;
    }
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
