package bitcamp.myapp;

import bitcamp.myapp.SongHandler.SongHandler;
import bitcamp.myapp.SongHandler.SongReviewBoardHandler;
import bitcamp.myapp.util.ArrayList;
import bitcamp.myapp.util.LinkedList;
import bitcamp.myapp.util.Prompt;

public class App {

    public static void main(String[] args) {

        // 기본 생성자를 이용해 Prompt 인스턴스를 준비한다.
        // => 기본 생성자는 Scanner를 키보드와 연결한다. OK
        Prompt prompt = new Prompt();

        SongHandler songHandler = new SongHandler(prompt, "노래", new ArrayList());
        SongReviewBoardHandler songReviewBoardHandler = new SongReviewBoardHandler(prompt, "게시글", new LinkedList());


        printTitle();

        printMenu();

        while (true) {
            String menuNo = prompt.inputString("메인> ");
            if (menuNo.equals("0")) {
                break;
            } else if (menuNo.equals("menu")) {
                printMenu();
            } else if (menuNo.equals("1")) {
                songHandler.execute();
            } else if (menuNo.equals("2")) {
                songReviewBoardHandler.execute();
            } else {
                System.out.println("메뉴 번호가 옳지 않습니다!");
            }
        }

        prompt.close();
    }

    static void printMenu() {
        System.out.println("1. 노래");
        System.out.println("2. 노래 감상 게시글");
        System.out.println("0. 종료");
    }

    static void printTitle() {
        System.out.println("나의 목록 관리 시스템");
        System.out.println("----------------------------------");
    }

}
