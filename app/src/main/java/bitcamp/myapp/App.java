package bitcamp.myapp;

import bitcamp.myapp.SongHandler.SongHandler;
import bitcamp.myapp.SongHandler.SongReviewBoardHandler;
import bitcamp.myapp.util.ArrayList;
import bitcamp.myapp.util.LinkedList;
import bitcamp.myapp.util.MenuPrompt;

public class App {

    public static void main(String[] args) {

        // 기본 생성자를 이용해 Prompt 인스턴스를 준비한다.
        // => 기본 생성자는 Scanner를 키보드와 연결한다. OK
        MenuPrompt prompt = new MenuPrompt();
        prompt.appendBreadcrumb("메인", getMenu());

        SongHandler songHandler = new SongHandler(prompt, "노래", new ArrayList());
        SongReviewBoardHandler songReviewBoardHandler = new SongReviewBoardHandler(prompt, "게시글", new LinkedList());

        printTitle();

        prompt.printMenu();
        
        label:
        while (true) {
            String menuNo = prompt.inputMenu();
            switch (menuNo) {
                case "0":
                    System.out.println("프로그램을 종료합니다!");
                    break label;
                case "1":
                    songHandler.execute();
                    break;
                case "2":
                    songReviewBoardHandler.execute();
                    break;
            }
        }
        
        prompt.close();
    }

    static void printMenu() {
        System.out.println("1. 노래");
        System.out.println("2. 노래 감상 게시글");
        System.out.println("0. 종료");
    }
    
    static String getMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("1. 노래\n");
        menu.append("2. 노래 감상평\n");
        menu.append("0. 종료\n");
        return menu.toString();
    }

    static void printTitle() {
        System.out.println("나의 목록 관리 시스템");
        System.out.println("----------------------------------");
    }

}
