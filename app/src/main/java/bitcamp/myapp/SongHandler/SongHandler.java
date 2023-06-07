package bitcamp.myapp.SongHandler;

import bitcamp.myapp.util.Prompt;

public class SongHandler {

    public static final int MAX_SIZE = 1000;

    public static int[] no = new int[MAX_SIZE];
    public static String[] title = new String[MAX_SIZE];
    public static String[] singer = new String[MAX_SIZE];
    public static String[] album = new String[MAX_SIZE];
    public static String[] genre = new String[MAX_SIZE];
    public static int[] year = new int[MAX_SIZE];
    public static boolean[] like = new boolean[MAX_SIZE];

    public static int songId = 1;
    public static int length = 0;

    public static boolean LIKE = true;
    public static boolean UNLIKE = false;

    static final String POP = "팝";
    static final String ROCK = "락";
    static final String ELECTRONIC = "EDM";
    static final String BALLAD = "발라드";
    static final String DANCE = "댄스";
    static final String HIPHOP = "힙합";

    public static void inputSong() {

        no[length] = songId;
        title[length] = Prompt.inputString("노래 이름은 무엇입니까? ");
        singer[length] = Prompt.inputString("가수는 누구입니까? ");
        album[length] = Prompt.inputString("앨범 이름은 무엇입니까? ");
        year[length] = Integer.parseInt(Prompt.inputString("발표된 연도는 몇년도입니까? "));

        String str = Prompt.inputString("이 노래를 좋아하십니까?(Y/n) ");

        if (str.equals("n")) {
            like[length] = UNLIKE;
        } else {
            like[length] = LIKE;
        }

        while (true) {
            str = Prompt.inputString("이 노래의 장르는 무엇입니까?(팝, 락, EDM, 발라드, 댄스, 힙합) ");
            boolean b = true;

            switch (str) {
                case POP:
                    genre[length] = POP;
                    b = false;
                    break;
                case ROCK:
                    genre[length] = ROCK;
                    b = false;
                    break;
                case ELECTRONIC:
                    genre[length] = ELECTRONIC;
                    b = false;
                    break;
                case BALLAD:
                    genre[length] = BALLAD;
                    b = false;
                    break;
                case HIPHOP:
                    genre[length] = HIPHOP;
                    b = false;
                    break;
                default:
                    System.out.println("등록되지 않은 장르입니다.");
            }
            if (!b) {
                break;
            }

        }

        songId++;
        length++;

    }

    public static void printSong() {

        //번호 제목 가수 앨범 장르 연도 좋아요

        for (int i = 0; i < length; i++) {
            if(like[i]) {
                System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", no[i], title[i], singer[i], album[i], genre[i], year[i], "좋아요!");
            } else {
                System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", no[i], title[i], singer[i], album[i], genre[i], year[i], "싫어요");
            }
        }

    }

    public static boolean available() {
        return length < MAX_SIZE;
    }

}

