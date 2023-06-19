package bitcamp.myapp.SongHandler;

import bitcamp.myapp.util.List;
import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.Song;

public class SongHandler implements Handler {

    public static final String POP = "팝";
    public static final String ROCK = "락";
    public static final String ELECTRONIC = "EDM";
    public static final String BALLAD = "발라드";
    public static final String DANCE = "댄스";
    public static final String HIPHOP = "힙합";

    public static final boolean LIKE = true;
    public static final boolean UNLIKE = false;

    public String title;
    private Prompt prompt = new Prompt();

    private List list;

    public SongHandler(Prompt prompt, String title, List list) {
        this.prompt = prompt;
        this.title = title;
        this.list = list;
    }

    private static void printMenu() {
        System.out.println("1. 등록");
        System.out.println("2. 목록");
        System.out.println("3. 조회");
        System.out.println("4. 변경");
        System.out.println("5. 삭제");
        System.out.println("0. 이전");
    }

    @Override
    public void execute() {
        printMenu();

        while (true) {
            String menuNo = prompt.inputString(String.format("%s> ", this.title));
            if (menuNo.equals("0")) {
                return;
            } else if (menuNo.equals("menu")) {
                printMenu();
            } else if (menuNo.equals("1")) {
                inputSong();
            } else if (menuNo.equals("2")) {
                printSongs();
            } else if (menuNo.equals("3")) {
                viewSong();
            } else if (menuNo.equals("4")) {
                updateSong();
            } else if (menuNo.equals("5")) {
                deleteSong();
            }  else {
                System.out.println("메뉴 번호가 옳지 않습니다!");
            }
        }

    }

    public void inputSong() {

        String title = this.prompt.inputString("노래 이름은 무엇입니까? ");
        String singer = prompt.inputString("가수는 누구입니까? ");
        String album = prompt.inputString("앨범 이름은 무엇입니까? ");
        int year = Integer.parseInt(prompt.inputString("발표된 연도는 몇년도입니까? "));
        boolean like = true;

        String str = prompt.inputString("이 노래를 좋아하십니까?(Y/n) ");

        if (str.equals("n")) {
            like = UNLIKE;
        } else {
            like = LIKE;
        }

        String genre = "";
        while (true) {
            genre = prompt.inputString("이 노래의 장르는 무엇입니까?(팝, 락, EDM, 발라드, 댄스, 힙합) ");
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
        Song song = new Song();
        song.setTitle(title); song.setSinger(singer); song.setAlbum(album); song.setGenre(genre);
        song.setYear(year); song.setLike(like);
        this.list.add(song);
        System.out.printf("%s의 노래 %s(이)가 등록되었습니다!\n", song.singer, song.title);
    }

    public void printSongs() {

         for (int i = 0; i < this.list.size(); i++) {
             Song s = (Song) this.list.get(i);
             printASong(s.getId());
         }

    }

    public void printASong(int songId) {

        Song s = this.findBy(songId);
        if (s == null) {
            System.out.println("없는 노래입니다.");
        }

        if (!s.isLike()) {
            System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", s.getId(), s.getTitle(), s.getSinger(), s.getAlbum(), s.getGenre(), s.getYear(), "싫어요");
        } else {
            System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", s.getId(), s.getTitle(), s.getSinger(), s.getAlbum(), s.getGenre(), s.getYear(), "싫어요");
        }

    }

    public void viewSong() {

        int songId = Integer.parseInt(prompt.inputString("노래 번호? "));

        Song s = this.findBy(songId);
        if (s == null) {
            System.out.printf("%d번의 번호를 가진 노래는 없습니다!%n", songId);
            return;
        }

        printASong(s.getId());
    }

    public void updateSong() {

        int songId = Integer.parseInt(prompt.inputString("노래 번호? "));
        printASong(songId);
        Song s = this.findBy(songId);

        if (s == null) {
            System.out.println("해당 번호의 노래가 없습니다!");
            return;
        }

        String toChange = prompt.inputString("어떤 항목을 바꾸시겠습니까? (제목 가수 앨범 장르 연도 좋아요) ");

        switch (toChange) {
            case "제목" -> {
                s.setTitle(prompt.inputString(String.format("%s -> ", s.getTitle())));
                return;
            }
            case "가수" -> {
                s.setSinger(prompt.inputString(String.format("%s -> ", s.getSinger())));
                return;
            }
            case "앨범" -> {
                s.setAlbum(prompt.inputString(String.format("%s -> ", s.getAlbum())));
                return;
            }
            case "장르" -> {
                s.setGenre(prompt.inputString(String.format("%s -> (팝, 락, EDM, 발라드, 댄스, 힙합 중 1)", s.getGenre())));
                return;
            }
            case "연도" -> {
                s.setYear(Integer.parseInt(prompt.inputString(String.format("%s -> ", s.year))));
                return;
            }
            case "좋아요" -> {
                if (s.isLike()) {
                    s.setLike(!s.isLike());
                    System.out.printf("좋아요 상태가 %s로 바뀌었습니다.\n", "싫어요");
                } else {
                    s.like = !s.like;
                    System.out.printf("좋아요 상태가 %s로 바뀌었습니다.\n", "좋아요");
                }
                return;
            }
            default -> System.out.println("잘못된 입력입니다.");
        }

        System.out.println("해당 번호의 노래가 없습니다!");
    }

    public void deleteSong() {

        int songId = prompt.inputInt("삭제할 노래의 번호? ");
        if (!list.remove(findBy(songId))) {
            System.out.println("해당 번호의 노래가 없습니다!");
        }
    }
    
    private Song findBy(int id) {
        for (int i = 0; i < this.list.size(); i++) {
            Song s = (Song) this.list.get(i);
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

}

