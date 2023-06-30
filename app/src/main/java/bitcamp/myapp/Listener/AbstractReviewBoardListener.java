package bitcamp.myapp.Listener;

import bitcamp.myapp.util.ActionListener;
import bitcamp.myapp.vo.ReviewBoard;

import java.util.List;

public abstract class AbstractReviewBoardListener implements ActionListener {

  protected List<ReviewBoard> list;

  public AbstractReviewBoardListener(List<ReviewBoard> list) {
    this.list = list;
  }

  protected ReviewBoard findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      ReviewBoard b = (ReviewBoard) this.list.get(i);
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }

}
