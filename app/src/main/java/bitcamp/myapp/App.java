package bitcamp.myapp;

import bitcamp.myapp.SongHandler.SongHandler;
import bitcamp.myapp.util.Prompt;

public class App {

    public static void main(String[] args) {

        printTitle();

        printMenu();

        while (true) {
            String songNo = Prompt.inputString("메뉴 선택> ");
            if (songNo.equals("6")) {
                break;
            } else if (songNo.equals("menu")) {
                printMenu();
            } else if (songNo.equals("1")) {
                SongHandler.inputSong();
            } else if (songNo.equals("2")) {
                SongHandler.printSongs();
            } else if (songNo.equals("3")) {
                SongHandler.viewSong();
            } else if (songNo.equals("4")) {
                SongHandler.updateSong();
            } else if (songNo.equals("5")) {
                SongHandler.deleteSong();
            } else {
                System.out.println(songNo);
            }
        }

        Prompt.scannerCloser();
    }

    static void printMenu() {
        System.out.println("1. 노래 등록");
        System.out.println("2. 노래 목록");
        System.out.println("3. 노래 조회");
        System.out.println("4. 노래 변경");
        System.out.println("5. 노래 삭제");
        System.out.println("6. 종료");
    }

    static void printTitle() {
        System.out.println("나의 노래 관리 시스템");
        System.out.println("----------------------------------");
    }

}
