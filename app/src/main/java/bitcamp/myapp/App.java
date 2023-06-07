package bitcamp.myapp;

import bitcamp.myapp.SongHandler.SongHandler;
import bitcamp.myapp.util.Prompt;

public class App {

    public static void main(String[] args) {

        printTitle();

        while (SongHandler.available()) {
            SongHandler.inputSong();
            if (!promptContinue()) {
                System.out.println("--------------------------------");
                break;
            }
            System.out.println("--------------------------------");
        }

        SongHandler.printSong();

        Prompt.scannerCloser();
    }

    static void printTitle() {
        System.out.println("노래 목록 관리 시스템");
        System.out.println("----------------------------------");
    }

    static boolean promptContinue() {
        String response = Prompt.inputString("계속 하시겠습니까?(Y/n) ");
        if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
            return false;
        }
        return true;
    }

}
