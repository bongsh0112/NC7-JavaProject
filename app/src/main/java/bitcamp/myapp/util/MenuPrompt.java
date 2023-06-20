package bitcamp.myapp.util;

public class MenuPrompt extends Prompt {
  
  private Stack menus = new Stack();
  private Stack breadCrumbs = new Stack();
  private Queue cmdHistory = new Queue();
  
  public void appendBreadcrumb(String title, String menu) {
    this.breadCrumbs.push(title);
    this.menus.push(menu);
  }
  
  public void removeBreadcrumb() {
    this.breadCrumbs.pop();
    this.menus.pop();
  }
  
  public void printMenu() {
    System.out.println(menus.peek());
  }
  
  public void printCmdHistory() {
    System.out.println("-------HISTORY-------");
    for (int i = 0; i < cmdHistory.size(); i++) {
      System.out.println(cmdHistory.get(i));
    }
    System.out.println("-------HISTORY-------");
  }
  
  public String inputMenu() { // 사용자가 cmd 입력 시 일어나는 이벤트 핸들러 -> 프롬프트 변경, history, menu 입력시 그에 맞게 출력, history queue 관리
    StringBuilder titleBuilder = new StringBuilder();
    for (int i = 0; i < this.breadCrumbs.size(); i++) {
      if (titleBuilder.length() > 0) {
        titleBuilder.append("/");
      }
      titleBuilder.append(this.breadCrumbs.get(i));
    }
    titleBuilder.append("> ");
    // ㄴ> 메뉴를 선택하면 프롬프트가 선택한 메뉴를 나타내도록 함
    
    String cmd = null;
    
    while (true) {
      cmd = this.inputString(titleBuilder.toString());
      if (cmd.equals("history")) {
        this.printCmdHistory(); // history 입력 시 history 출력
      } else if (cmd.equals("menu")) {
        this.printMenu(); // menu 입력 시 history 출력
      } else if (findMenuItem(cmd) == null) { // cmd로 메뉴의 항목 탐색 후 null이라면 경고문 출력
        System.out.println("메뉴 번호가 옳지 않습니다!");
      } else {
        break;
      }
    }
    // ㄴ> cmd를 입력받아 값 판단 후 cmd에 맞는 출력 보이기
    
    // 사용자가 입력한 명령어를 history에 보관
    if (this.cmdHistory.size() == 10) {
      // 명령어 목록은 최대 10개만 유지한다.
      // 10개를 초과할 경우 맨앞의 기록을 삭제한다.
      this.cmdHistory.poll();
    }
    String menuItem = findMenuItem(cmd); // cmd와 일치하는 메뉴 찾기
    if (menuItem != null) { // cmd와 일치하는 메뉴가 없다면 history에 방금 입력한 것 추가
      this.cmdHistory.offer(titleBuilder.toString() + ": " + menuItem);
    } else { // cmd와 일치하다면 cmd 추가
      this.cmdHistory.offer(cmd);
    }
    return cmd;
  }
  
  private String findMenuItem(String cmd) { // cmd를 입력받아 menu 중 cmd와 일치하는 메뉴를 리턴함.
    String menuTitle = null;
    
    String menu = (String) this.menus.peek(); // menu queue의 최신 menu 가져오기
    String[] menuItems = menu.split("\n"); // "1. 등록 \n" 형식이므로 \n으로 구별
    for (String item : menuItems) { // 해당 cmd의 메뉴를 찾는다
      if (item.startsWith(cmd)) {
        return item;
      }
    }
    return menuTitle;
  }
  
}
