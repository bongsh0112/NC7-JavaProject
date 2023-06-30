package bitcamp.myapp.Listener;

import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.vo.ReviewBoard;

import java.util.List;

public class ReviewBoardListListener extends AbstractReviewBoardListener {
  public ReviewBoardListListener(List<ReviewBoard> list) {
    super(list);
  }
  
  @Override
  public void service(BreadCrumbPrompt prompt) {
    for (int i = 0; i < list.size(); i++) {
      ReviewBoard b = (ReviewBoard) this.list.get(i);
      System.out.printf("%d번 노래 : %s %s %s %d %tY-%6$tm-%6$td\n",
              b.getNo(), b.getSong(), b.getContent(), b.getSinger(), b.getViewCount(), b.getCreatedDate());
    }
  }
}
