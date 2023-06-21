package bitcamp.myapp.Listener;

import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.ReviewBoard;

public class ReviewBoardDetailListener extends AbstractReviewBoardListener {
  
  public ReviewBoardDetailListener(List list) {
    super(list);
  }
  
  @Override
  public void service(BreadCrumbPrompt prompt) {
    ReviewBoard board = findBy(prompt.inputInt("조회할 노래 리뷰 게시글의 번호를 입력하세요. "));
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    board.setViewCount(board.getViewCount() + 1);
    System.out.printf("%d번 노래 : %s %s %s %d %tY-%6$tm-%6$td\n",
            board.getNo(), board.getSong(), board.getContent(), board.getSinger(), board.getViewCount(), board.getCreatedDate());
  }
}
