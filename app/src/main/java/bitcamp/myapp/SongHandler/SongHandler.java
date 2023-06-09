package bitcamp.myapp.SongHandler;

import bitcamp.myapp.SongHandler.vo.Song;
import bitcamp.myapp.util.Prompt;

public class SongHandler {

    public static final int MAX_SIZE = 100;

    static Song[] songs = new Song[MAX_SIZE];
    public static int songId = 1;
    public static int length = 0;
    public static int deleteCount = 0;

    public static final boolean LIKE = true;
    public static final boolean UNLIKE = false;

    static final String POP = "팝";
    static final String ROCK = "락";
    static final String ELECTRONIC = "EDM";
    static final String BALLAD = "발라드";
    static final String DANCE = "댄스";
    static final String HIPHOP = "힙합";

    public static void inputSong() {

        if(!available()) {
            System.out.println("더 이상 입력할 수 없습니다!");
        }

        Song song = new Song();

        song.no = songId;
        song.title = Prompt.inputString("노래 이름은 무엇입니까? ");
        song.singer = Prompt.inputString("가수는 누구입니까? ");
        song.album = Prompt.inputString("앨범 이름은 무엇입니까? ");
        song.year = Integer.parseInt(Prompt.inputString("발표된 연도는 몇년도입니까? "));

        String str = Prompt.inputString("이 노래를 좋아하십니까?(Y/n) ");

        if (str.equals("n")) {
            song.like = UNLIKE;
        } else {
            song.like = LIKE;
        }

        while (true) {
            str = Prompt.inputString("이 노래의 장르는 무엇입니까?(팝, 락, EDM, 발라드, 댄스, 힙합) ");
            boolean b = true;

            switch (str) {
                case POP -> {
                    song.genre = POP;
                    b = false;
                }
                case ROCK -> {
                    song.genre = ROCK;
                    b = false;
                }
                case ELECTRONIC -> {
                    song.genre = ELECTRONIC;
                    b = false;
                }
                case BALLAD -> {
                    song.genre = BALLAD;
                    b = false;
                }
                case DANCE -> {
                    song.genre = DANCE;
                    b = false;
                }
                case HIPHOP -> {
                    song.genre = HIPHOP;
                    b = false;
                }
                default -> System.out.println("등록되지 않은 장르입니다.");
            }
            if (!b) {
                break;
            }

        }
        System.out.printf("%s의 노래 %s(이)가 등록되었습니다!\n", song.singer, song.title);
        songs[length] = song;
        length++;
        songId++;
    }

    public static void printSongs() {

        //번호 제목 가수 앨범 장르 연도 좋아요
        for (int i = 0; i < length + count; i++) {
            printASong(i + 1);
        }

    }

    public static void printASong(int songNo) {

        int idx = indexOf(songNo);

        if (idx >= 0) {
            if (!songs[idx].like) {
                System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", songs[idx].no, songs[idx].title, songs[idx].singer, songs[idx].album, songs[idx].genre, songs[idx].year, "싫어요");
            } else {
                System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", songs[idx].no, songs[idx].title, songs[idx].singer, songs[idx].album, songs[idx].genre, songs[idx].year, "좋아요");
            }
        }

    }

    public static void viewSong() {

        int songNo = Integer.parseInt(Prompt.inputString("노래 번호? "));
        for (int i = 0; i < length; i++) {
            if (songs[i].no == songNo) {
                printASong(i + 1);
                return;
            }
        }
        System.out.printf("%d번의 번호를 가진 노래는 없습니다!%n", songNo);
    }

    public static void updateSong() {

        int songNo = Integer.parseInt(Prompt.inputString("노래 번호? "));
        printASong(songNo);

        for (int i = 0; i < length; i++) {
            if (songs[i].no == songNo) {
                while (true) {
                    String toChange = Prompt.inputString("어떤 항목을 바꾸시겠습니까? (제목 가수 앨범 장르 연도 좋아요) ");
                    switch (toChange) {
                        case "제목" -> {
                            songs[i].title = Prompt.inputString(String.format("%s -> ", songs[i].title));
                            return;
                        }
                        case "가수" -> {
                            songs[i].singer = Prompt.inputString(String.format("%s -> ", songs[i].singer));
                            return;
                        }
                        case "앨범" -> {
                            songs[i].album = Prompt.inputString(String.format("%s -> ", songs[i].album));
                            return;
                        }
                        case "장르" -> {
                            songs[i].genre = Prompt.inputString(String.format("%s -> (팝, 락, EDM, 발라드, 댄스, 힙합 중 1)", songs[i].genre));
                            return;
                        }
                        case "연도" -> {
                            songs[i].year = Integer.parseInt(Prompt.inputString(String.format("%s -> ", songs[i].year)));
                            return;
                        }
                        case "좋아요" -> {
                            if (songs[i].like) {
                                songs[i].like = !songs[i].like;
                                System.out.printf("좋아요 상태가 %s로 바뀌었습니다.\n", "싫어요");
                            } else {
                                songs[i].like = !songs[i].like;
                                System.out.printf("좋아요 상태가 %s로 바뀌었습니다.\n", "좋아요");
                            }
                            return;
                        }
                        default -> System.out.println("잘못된 입력입니다.");
                    }
                }

            }
        }
        System.out.println("해당 번호의 노래가 없습니다!");
    }

    public static void deleteSong() {

        int memberNo = Prompt.inputInt("삭제할 노래의 번호? ");

        int deletedIndex = indexOf(memberNo);
        if (deletedIndex == -1) {
            System.out.println("해당 번호의 노래가 없습니다!");
            return;
        }
        
        for (int i = deletedIndex; i < length - 1; i++) {
            songs[i] = songs[i + 1];
        }
        
        songs[length] = null;
        length--;
        deleteCount++;
    }

    public static int indexOf(int songNo) {
        for (int i = 0; i < length; i++) {
            Song s = songs[i];
            if (s.no == songNo) {
                return i;
            }
        }
        return -1;
    }

    public static boolean available() {
        return length < MAX_SIZE;
    }

}

