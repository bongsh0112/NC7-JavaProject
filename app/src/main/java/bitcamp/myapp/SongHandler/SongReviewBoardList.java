package bitcamp.myapp.SongHandler;

import bitcamp.myapp.vo.Song;
import bitcamp.myapp.vo.SongReviewBoard;

public class SongReviewBoardList {

  private final int DEFAULT_SIZE = 100;

  public static int length = 0;
  private SongReviewBoard[] boards = new SongReviewBoard[DEFAULT_SIZE];

  public void add(SongReviewBoard board) {
    if (length == DEFAULT_SIZE) {
      increase();
    }
    boards[length++] = board;
  }

  public void increase() {
    SongReviewBoard[] arr = new SongReviewBoard[DEFAULT_SIZE * 2];
    for (int i = 0; i < length; i++) {
      arr[i] = boards[i];
    }
    boards = arr;
  }

  public SongReviewBoard[] list() {
    SongReviewBoard[] tempArr = new SongReviewBoard[length];
    for (int i = 0; i < length; i++) {
      tempArr[i] = boards[i];
    }
    return tempArr;
  }

  public SongReviewBoard get(int boardNo) {
    for (int i = 0; i < length; i++) {
      if (boards[i].getNo() == boardNo) {
        return boards[i];
      }
    }
    return null;
  }

  public int indexOf(int boardNo) {
    for (int i = 0; i < length; i++) {
      SongReviewBoard board = this.boards[i];
      if (board.getNo() == boardNo) {
        return i;
      }
    }
    return -1;
  }

  public boolean delete(int boardNo) {
    int deletedIndex = indexOf(boardNo);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      boards[i] = boards[i + 1];
    }

    boards[length] = null;
    length--;
    return true;
  }
}
