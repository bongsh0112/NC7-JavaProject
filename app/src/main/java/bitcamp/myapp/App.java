package bitcamp.myapp;

import bitcamp.myapp.Listener.*;
import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.util.Menu;
import bitcamp.myapp.util.MenuGroup;
import bitcamp.myapp.vo.AutoIncrement;
import bitcamp.myapp.vo.ReviewBoard;
import bitcamp.myapp.vo.Song;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
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
        loadJson("song.json", songList, Song.class);
        loadJson("reviewBoard.json", reviewBoardList, ReviewBoard.class);
    }
    
    private void saveData() {
        saveJson("song.json", songList);
        saveJson("reviewBoard.json", reviewBoardList);
    }
    
    private <T> void loadJson(String filename, List<T> list, Class<T> clazz) { // Json 파일을 읽어온다
        try {
            FileReader in0 = new FileReader(filename);
            BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!
            
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
            
            in.close();
            
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Collection<T> objects = gson.fromJson(stringBuilder.toString(),
                    TypeToken.getParameterized(Collection.class, clazz).getType());
            /** JSON 배열 -> Collection 객체로 변환. TypeToken을 쓰는 이유는 Gson에서 요구하기 때문.
             * fromJson은 Json 배열과 Type 정보를 파라미터로 요구하는데, Type에는 Gson이 요구하는 TypeToken을 넣어준다.
             * TypeToken이 컬렉션 및 항목의 상세 정보를 리턴하면 Gson이 Collection 객체를 생성하고 그것이 objects이다.
             */
            
            list.addAll(objects);
            
            Class<?>[] interfaces = clazz.getInterfaces(); // parameter로 받은 clazz의 인터페이스들을 모두 불러온다.
            for (Class<?> info : interfaces) { // 불러온 인터페이스들에 대해
                if (info == AutoIncrement.class) { // 인터페이스가 AutoIncrement라면
                    AutoIncrement autoIncrement = (AutoIncrement) list.get(list.size() - 1); // list의 마지막 원소를 AutoIncrement 클래스로 형변환하여 autoIncrement 변수에 저장
                    autoIncrement.updateKey(); // list의 마지막 원소에 대해 updateKey => vo 클래스의 static id, no를 최신화
                    break;
                }
            }
            
        } catch (Exception e) {
            System.out.println(filename + " 파일을 읽는 중 오류 발생!");
        }
    }
    
    private void saveJson(String filename, List<?> list) {
        try {
            FileWriter out0 = new FileWriter(filename);
            BufferedWriter out = new BufferedWriter(out0);
            
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
            out.write(gson.toJson(list));
            
            out.close();
            
        } catch (Exception e) {
            System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
        }
    }
    
}
