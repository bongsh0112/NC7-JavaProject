package bitcamp.myapp.SongHandler;

import bitcamp.myapp.util.List;
import bitcamp.myapp.util.MenuPrompt;
import bitcamp.myapp.vo.SongReviewBoard;

public class SongReviewBoardHandler implements Handler {

  // 인스턴스에 상관없이 공통으로 사용하는 필드라면 스태틱 필드로 선언한다.


  // 인스턴스 마다 별개로 관리해야 할 데이터라면 논스태틱 필드(인스턴스 필드)로 선언한다.
  private MenuPrompt prompt;
  private String title;
  
  private List list;

  public SongReviewBoardHandler(MenuPrompt prompt, String title, List list) {
    this.prompt = prompt;
    this.title = title;
    this.list = list;
  }

  @Override
  public void execute() {
    prompt.appendBreadcrumb("게시글", getMenu());
    prompt.printMenu();

    while (true) {
      String menuNo = prompt.inputMenu(); // menu, history, exception 기능은 여기서 관리
      switch (menuNo) {
        case "0" -> {
          prompt.removeBreadcrumb();
          return;
        }
        case "1" -> {
          inputBoard();
          break;
        }
        case "2" -> {
          printBoards();
          break;
        }
        case "3" -> {
          viewBoard();
          break;
        }
        case "4" -> {
          updateBoard();
          break;
        }
        case "5" -> {
          deleteBoard();
          break;
        }
      }
    }
  }
  
  private static String getMenu() {
    StringBuilder menu = new StringBuilder();
    
    menu.append("1. 등록\n");
    menu.append("2. 목록\n");
    menu.append("3. 조회\n");
    menu.append("4. 변경\n");
    menu.append("5. 삭제\n");
    menu.append("0. 메인\n");
    
    return menu.toString(); // StringBuilder를 이용하여 String으로 위의 것들을 저장한다. 분리할 때는 \n로 분리하면 쉬울 것.
  }

  public void inputBoard() {
    SongReviewBoard board = new SongReviewBoard();

    board.setSong(this.prompt.inputString("노래 이름은 무엇입니까? "));
    board.setSinger(this.prompt.inputString("가수는 누구입니까? "));
    board.setContent(this.prompt.inputString("감상평을 작성해주세요. "));
    board.setPassword(this.prompt.inputString("비밀번호를 입력해주세요. "));

    this.list.add(board);
  }

  public void printBoards() {
    for (int i = 0; i < list.size(); i++) {
      SongReviewBoard board = (SongReviewBoard) this.list.get(i);
      printABoard(board.getNo());
    }
  }

  //  번호 노래 감상평 가수 비밀번호 조회수 쓴날짜
  public void printABoard(int boardId) {
    SongReviewBoard b = findBy(boardId);
    System.out.printf("%d번 노래 : %s %s %s %d %tY-%6$tm-%6$td\n",
            b.getNo(), b.getSong(), b.getContent(), b.getSinger(), b.getViewCount(), b.getCreatedDate());
  }

  public void viewBoard() { // 조회 시 viewCount++
    int boardNo = this.prompt.inputInt("조회할 노래 리뷰 게시글의 번호를 입력하세요. ");
    SongReviewBoard board = findBy(boardNo);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    board.setViewCount(board.getViewCount() + 1);
    printABoard(board.getNo());
  }

  public void updateBoard() {
    int boardNo = this.prompt.inputInt("감상평을 수정할 게시글의 번호를 입력하세요. ");
    SongReviewBoard board = findBy(boardNo);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String newContent = this.prompt.inputString("새 감상평을 입력하세요.\n>> ");
    board.setContent(newContent);
  }

  public void deleteBoard() {
    int boardNo = this.prompt.inputInt("삭제할 게시글의 번호를 입력하세요. ");
    if (!list.remove(findBy(boardNo))) {
      System.out.println("해당 번호의 게시글이 없습니다.");
    }
  }
  
  private SongReviewBoard findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      SongReviewBoard b = (SongReviewBoard) this.list.get(i);
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }
}
