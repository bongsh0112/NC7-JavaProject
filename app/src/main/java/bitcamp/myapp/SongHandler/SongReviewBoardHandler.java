package bitcamp.myapp.SongHandler;

import bitcamp.myapp.util.List;
import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.SongReviewBoard;

public class SongReviewBoardHandler implements Handler {

  // 인스턴스에 상관없이 공통으로 사용하는 필드라면 스태틱 필드로 선언한다.


  // 인스턴스 마다 별개로 관리해야 할 데이터라면 논스태틱 필드(인스턴스 필드)로 선언한다.
  private Prompt prompt;
  private String title;
  
  private List list;

  public SongReviewBoardHandler(Prompt prompt, String title, List list) {
    this.prompt = prompt;
    this.title = title;
    this.list = list;
  }

  @Override
  public void execute() {
    printMenu();

    while (true) {
      String menuNo = prompt.inputString(String.format("%s> ", this.title));
      if (menuNo.equals("0")) {
        return;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        inputBoard();
      } else if (menuNo.equals("2")) {
        printBoards();
      } else if (menuNo.equals("3")) {
        viewBoard();
      } else if (menuNo.equals("4")) {
        updateBoard();
      } else if (menuNo.equals("5")) {
        deleteBoard();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  private static void printMenu() {
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 조회");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 이전");
  }

  public void inputBoard() {
    SongReviewBoard board = new SongReviewBoard();

    String song = this.prompt.inputString("노래 이름은 무엇입니까? ");
    String singer = prompt.inputString("가수는 누구입니까? ");
    String content = prompt.inputString("감상평을 작성해주세요. ");
    String password = prompt.inputString("비밀번호를 입력해주세요. ");

    board.setSong(song); board.setSinger(singer); board.setContent(content); board.setPassword(password);
    list.add(board);
  }

  public void printBoards() {
    for (int i = 0; i < list.size(); i++) {
      SongReviewBoard board = (SongReviewBoard) this.list.get(i);
      printABoard(board);
    }
  }

  //  번호 노래 감상평 가수 비밀번호 조회수 쓴날짜
  public void printABoard(SongReviewBoard board) {
    SongReviewBoard b = findBy(board.getNo());
    System.out.println(String.format("%d번 노래 : %s %s %s %d %%tY-%5$tm-%5$td",
            b.getNo(), b.getSong(), b.getContent(), b.getSinger(), b.getViewCount(), b.getCreatedDate()));
  }

  public void viewBoard() { // 조회 시 viewCount++
    int boardNo = this.prompt.inputInt("조회할 노래 리뷰 게시글의 번호를 입력하세요. ");
    SongReviewBoard board = findBy(boardNo);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    board.setViewCount(board.getViewCount() + 1);
    printABoard(board);
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
