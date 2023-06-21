package bitcamp.myapp;

import bitcamp.myapp.Listener.*;
import bitcamp.myapp.util.*;

public class App {

    public static void main(String[] args) {
        
        BreadCrumbPrompt prompt = new BreadCrumbPrompt();
        List songList = new ArrayList();
        List reviewBoardList = new LinkedList();
        
        MenuGroup mainMenu = new MenuGroup("메인");
        
        MenuGroup memberMenu = new MenuGroup("회원");
        memberMenu.add(new Menu("등록", new SongAddListener(songList)));
        memberMenu.add(new Menu("목록", new SongListListener(songList)));
        memberMenu.add(new Menu("조회", new SongDetailListener(songList)));
        memberMenu.add(new Menu("변경", new SongUpdateListener(songList)));
        memberMenu.add(new Menu("삭제", new SongDeleteListener(songList)));
        mainMenu.add(memberMenu);
        
        MenuGroup boardMenu = new MenuGroup("게시글");
        boardMenu.add(new Menu("등록", new ReviewBoardAddListener(reviewBoardList)));
        boardMenu.add(new Menu("목록", new ReviewBoardAddListener(reviewBoardList)));
        boardMenu.add(new Menu("조회", new ReviewBoardAddListener(reviewBoardList)));
        boardMenu.add(new Menu("변경", new ReviewBoardAddListener(reviewBoardList)));
        boardMenu.add(new Menu("삭제", new ReviewBoardAddListener(reviewBoardList)));
        mainMenu.add(boardMenu);
        
        printTitle();
        
        mainMenu.execute(prompt);
        
        prompt.close();
    }
    
    static void printTitle() {
        System.out.println("나의 목록 관리 시스템");
        System.out.println("----------------------------------");
    }

}
