package bitcamp.myapp.Listener;

import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.ReviewBoard;

public class ReviewBoardAddListener extends AbstractReviewBoardListener {
  public ReviewBoardAddListener(List list) {
    super(list);
  }
  
  @Override
  public void service(BreadCrumbPrompt prompt) {
    
    ReviewBoard board = new ReviewBoard();
    
    board.setSong(prompt.inputString("노래 이름은 무엇입니까? "));
    board.setSinger(prompt.inputString("가수는 누구입니까? "));
    board.setContent(prompt.inputString("감상평을 작성해주세요. "));
    board.setPassword(prompt.inputString("비밀번호를 입력해주세요. "));
    
    this.list.add(board);
  }
  
}
