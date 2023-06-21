package bitcamp.myapp.Listener;

import bitcamp.myapp.util.ActionListener;
import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.ReviewBoard;

public abstract class AbstractReviewBoardListener implements ActionListener {

  protected List list;

  public AbstractReviewBoardListener(List list) {
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
