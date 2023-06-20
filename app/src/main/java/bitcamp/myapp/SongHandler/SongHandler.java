package bitcamp.myapp.SongHandler;

import bitcamp.myapp.util.List;
import bitcamp.myapp.util.MenuPrompt;
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
    private MenuPrompt prompt = new MenuPrompt();

    private List list;

    public SongHandler(MenuPrompt prompt, String title, List list) {
        this.prompt = prompt;
        this.title = title;
        this.list = list;
    }

    @Override
    public void execute() {
        
        prompt.appendBreadcrumb("노래", getMenu());
        prompt.printMenu();

        while (true) {
            String menuNo = prompt.inputMenu(); // menu, history, exception 기능은 여기서 관리
            switch (menuNo) {
                case "0" -> {
                    prompt.removeBreadcrumb();
                    return;
                }
                case "1" -> {
                    inputSong();
                    break;
                }
                case "2" -> {
                    printSongs();
                    break;
                }
                case "3" -> {
                    viewSong();
                    break;
                }
                case "4" -> {
                    updateSong();
                    break;
                }
                case "5" -> {
                    deleteSong();
                    break;
                }
            }
        }

    }
    
    private static String getMenu() {
        StringBuilder menu = new StringBuilder();
        
        menu.append("1. 등록\n");
        menu.append("2. 목록\n");
        menu.append("3. 조회\n");
        menu.append("4. 변경\n");
        menu.append("5. 삭제\n");
        menu.append("0. 메인\n");
        
        return menu.toString(); // StringBuilder를 이용하여 String으로 위의 것들을 저장한다. 분리할 때는 \n로 분리하면 쉬울 것.
    }

    public void inputSong() {

        String title = this.prompt.inputString("노래 이름은 무엇입니까? ");
        String singer = prompt.inputString("가수는 누구입니까? ");
        String album = prompt.inputString("앨범 이름은 무엇입니까? ");
        int year;
        while (true) {
            try {
                year = Integer.parseInt(prompt.inputString("발표된 연도는 몇년도입니까? "));
                break;
            } catch (NumberFormatException e){
                System.out.println("정확한 연도를 입력해주세요.");
                continue;
            }
        }
        
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
        System.out.printf("%d : %s의 노래 %s(이)가 등록되었습니다!\n", song.getId(), song.getSinger(), song.getTitle());
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
            System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", s.getId(), s.getTitle(), s.getSinger(), s.getAlbum(), s.getGenre(), s.getYear(), "좋아요");
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
                s.setYear(Integer.parseInt(prompt.inputString(String.format("%s -> ", s.getYear()))));
                return;
            }
            case "좋아요" -> {
                if (s.isLike()) {
                    s.setLike(!s.isLike());
                    System.out.printf("좋아요 상태가 %s로 바뀌었습니다.\n", "싫어요");
                } else {
                    s.setLike(!s.isLike());
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

