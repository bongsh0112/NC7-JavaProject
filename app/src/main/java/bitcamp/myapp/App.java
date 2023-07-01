package bitcamp.myapp;

import bitcamp.myapp.Listener.*;
import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.util.Menu;
import bitcamp.myapp.util.MenuGroup;
import bitcamp.myapp.vo.CsvObject;
import bitcamp.myapp.vo.ReviewBoard;
import bitcamp.myapp.vo.Song;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {
    
    BreadCrumbPrompt prompt = new BreadCrumbPrompt();
    List<Song> songList = new ArrayList<>();
    List<ReviewBoard> reviewBoardList = new LinkedList<>();
    
    MenuGroup mainMenu = new MenuGroup("메인");
    
    public App() {
        prepareMenu();
    }
    public static void main(String[] args) {
        new App().execute();
    }
    
    static void printTitle() {
        System.out.println("나의 목록 관리 시스템");
        System.out.println("----------------------------------");
    }
    
    public void execute() {
        printTitle();
        
        loadData();
        mainMenu.execute(prompt);
        saveData();
        
        prompt.close();
    }
    
    public void prepareMenu() {
        MenuGroup memberMenu = new MenuGroup("노래");
        memberMenu.add(new Menu("등록", new SongAddListener(songList)));
        memberMenu.add(new Menu("목록", new SongListListener(songList)));
        memberMenu.add(new Menu("조회", new SongDetailListener(songList)));
        memberMenu.add(new Menu("변경", new SongUpdateListener(songList)));
        memberMenu.add(new Menu("삭제", new SongDeleteListener(songList)));
        mainMenu.add(memberMenu);
        
        MenuGroup boardMenu = new MenuGroup("노래 감상평 게시판");
        boardMenu.add(new Menu("등록", new ReviewBoardAddListener(reviewBoardList)));
        boardMenu.add(new Menu("목록", new ReviewBoardListListener(reviewBoardList)));
        boardMenu.add(new Menu("조회", new ReviewBoardDetailListener(reviewBoardList)));
        boardMenu.add(new Menu("변경", new ReviewBoardUpdateListener(reviewBoardList)));
        boardMenu.add(new Menu("삭제", new ReviewBoardDeleteListener(reviewBoardList)));
        mainMenu.add(boardMenu);
    }
    
    private void loadData() {
        loadCsv("song.csv", songList, Song.class);
        loadCsv("reviewBoard.csv", reviewBoardList, ReviewBoard.class);
    }
    
    private void saveData() {
        saveCsv("song.csv", songList);
        saveCsv("reviewBoard.csv", reviewBoardList);
    }
    
    @SuppressWarnings("unchecked")
    private <T extends CsvObject> void loadCsv(String filename, List<T> list, Class<T> clazz) {
        try {
            Method factoryMethod = clazz.getDeclaredMethod("fromCsv", String.class);
            
            FileReader in0 = new FileReader(filename);
            BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!
            
            String line = null;
            
            while ((line = in.readLine()) != null) {
                list.add((T)factoryMethod.invoke(null, line)); // Reflection API를 사용하여 스태틱 메서드 호출
//                 list.add(clazz.fromCsv(line)); // 직접 스태틱 메서드 호출
            }
            
            in.close();
            
        } catch (Exception e) {
            System.out.println(filename + " 파일을 읽는 중 오류 발생!");
        }
    }
    
    private void saveCsv(String filename, List<? extends CsvObject> list) {
        try {
            FileWriter out0 = new FileWriter(filename);
            BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator(장식품) 역할 수행!
            PrintWriter out = new PrintWriter(out1); // <== Decorator(장식품) 역할 수행!
            
            for (CsvObject obj : list) {
                out.write(obj.toCsvString());
                // Board나 Member 클래스에 필드가 추가/변경/삭제되더라도
                // 여기 코드를 변경할 필요가 없다.
                // 이것이 Information Expert 설계를 적용하는 이유다!
                // 설계를 어떻게 하느냐에 따라 유지보수가 쉬워질 수도 있고,
                // 어려워질 수도 있다.
            }
            out.close();
            
        } catch (Exception e) {
            System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
        }
    }
    
}
