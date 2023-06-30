package bitcamp.myapp.Listener;

import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.vo.ReviewBoard;

import java.util.List;

public class ReviewBoardUpdateListener extends AbstractReviewBoardListener {
  
  public ReviewBoardUpdateListener(List<ReviewBoard> list) {
    super(list);
  }
  
  @Override
  public void service(BreadCrumbPrompt prompt) {
    int boardNo = prompt.inputInt("감상평을 수정할 게시글의 번호를 입력하세요. ");
    ReviewBoard board = findBy(boardNo);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    String newContent = prompt.inputString("새 감상평을 입력하세요.\n>> ");
    board.setContent(newContent);
  }
}
