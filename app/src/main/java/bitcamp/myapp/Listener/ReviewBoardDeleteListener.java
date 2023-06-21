package bitcamp.myapp.Listener;

import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.util.List;

public class ReviewBoardDeleteListener extends AbstractReviewBoardListener {
  
  public ReviewBoardDeleteListener(List list) {
    super(list);
  }
  
  @Override
  public void service(BreadCrumbPrompt prompt) {
    int boardNo = prompt.inputInt("삭제할 게시글의 번호를 입력하세요. ");
    if (!list.remove(findBy(boardNo))) {
      System.out.println("해당 번호의 게시글이 없습니다.");
    }
  }
}
