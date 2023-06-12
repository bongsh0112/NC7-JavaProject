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


        int no = songId;
        String title = Prompt.inputString("노래 이름은 무엇입니까? ");
        String singer = Prompt.inputString("가수는 누구입니까? ");
        String album = Prompt.inputString("앨범 이름은 무엇입니까? ");
        int year = Integer.parseInt(Prompt.inputString("발표된 연도는 몇년도입니까? "));
        boolean like = true;

        String str = Prompt.inputString("이 노래를 좋아하십니까?(Y/n) ");

        if (str.equals("n")) {
            like = UNLIKE;
        } else {
            like = LIKE;
        }

        String genre = "";
        while (true) {
            genre = Prompt.inputString("이 노래의 장르는 무엇입니까?(팝, 락, EDM, 발라드, 댄스, 힙합) ");
            boolean b = true;

            switch (genre) {
                case POP -> {
                    genre = POP;
                    b = false;
                }
                case ROCK -> {
                    genre = ROCK;
                    b = false;
                }
                case ELECTRONIC -> {
                    genre = ELECTRONIC;
                    b = false;
                }
                case BALLAD -> {
                    genre = BALLAD;
                    b = false;
                }
                case DANCE -> {
                    genre = DANCE;
                    b = false;
                }
                case HIPHOP -> {
                    genre = HIPHOP;
                    b = false;
                }
                default -> System.out.println("등록되지 않은 장르입니다.");
            }
            if (!b) {
                break;
            }

            //public Song(int no, String title, String singer, String album, String genre, int year, boolean like)

        }
        Song song = new Song(songId, title, singer, album, genre, year, like);
        System.out.printf("%s의 노래 %s(이)가 등록되었습니다!\n", song.singer, song.title);
        songs[length] = song;
        length++;
        songId++;
    }

    public static void printSongs() {

        //번호 제목 가수 앨범 장르 연도 좋아요
        for (int i = 0; i < length + deleteCount; i++) {
            printASong(i + 1);
        }

    }

    public static void printASong(int songNo) {

        int idx = indexOf(songNo);

        if (idx >= 0) {
            if (!songs[idx].isLike()) {
                System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", songs[idx].getNo(), songs[idx].getTitle(), songs[idx].getSinger(), songs[idx].getAlbum(), songs[idx].getGenre(), songs[idx].getYear(), "싫어요");
            } else {
                System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", songs[idx].getNo(), songs[idx].getTitle(), songs[idx].getSinger(), songs[idx].getAlbum(), songs[idx].getGenre(), songs[idx].getYear(), "싫어요");
            }
        }

    }

    public static void viewSong() {

        int songNo = Integer.parseInt(Prompt.inputString("노래 번호? "));
        for (int i = 0; i < length; i++) {
            if (songs[i].getNo() == songNo) {
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
            if (songs[i].getNo() == songNo) {
                while (true) {
                    String toChange = Prompt.inputString("어떤 항목을 바꾸시겠습니까? (제목 가수 앨범 장르 연도 좋아요) ");
                    switch (toChange) {
                        case "제목" -> {
                            songs[i].setTitle(Prompt.inputString(String.format("%s -> ", songs[i].getTitle())));
                            return;
                        }
                        case "가수" -> {
                            songs[i].setSinger(Prompt.inputString(String.format("%s -> ", songs[i].getSinger())));
                            return;
                        }
                        case "앨범" -> {
                            songs[i].setAlbum(Prompt.inputString(String.format("%s -> ", songs[i].getAlbum())));
                            return;
                        }
                        case "장르" -> {
                            songs[i].setGenre(Prompt.inputString(String.format("%s -> (팝, 락, EDM, 발라드, 댄스, 힙합 중 1)", songs[i].getGenre())));
                            return;
                        }
                        case "연도" -> {
                            songs[i].setYear(Integer.parseInt(Prompt.inputString(String.format("%s -> ", songs[i].year))));
                            return;
                        }
                        case "좋아요" -> {
                            if (songs[i].isLike()) {
                                songs[i].setLike(!songs[i].isLike());
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
            if (s.getNo() == songNo) {
                return i;
            }
        }
        return -1;
    }

    public static boolean available() {
        return length < MAX_SIZE;
    }

}

