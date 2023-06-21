package bitcamp.myapp.Listener;

import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.Song;

public class SongAddListener extends AbstractSongListener{
  
  public static final String POP = "팝";
  public static final String ROCK = "락";
  public static final String ELECTRONIC = "EDM";
  public static final String BALLAD = "발라드";
  public static final String DANCE = "댄스";
  public static final String HIPHOP = "힙합";
  public static final boolean LIKE = true;
  public static final boolean UNLIKE = false;
  
  public SongAddListener(List list) {
    super(list);
  }
  
  @Override
  public void service(BreadCrumbPrompt prompt) {
    
    Song song = new Song();
    
    song.setTitle(prompt.inputString("노래 이름은 무엇입니까? "));
    song.setSinger(prompt.inputString("가수는 누구입니까? "));
    song.setAlbum(prompt.inputString("앨범 이름은 무엇입니까? "));
    while (true) {
      try {
        song.setYear(Integer.parseInt(prompt.inputString("발표된 연도는 몇년도입니까? ")));
        break;
      } catch (NumberFormatException e){
        System.out.println("정확한 연도를 입력해주세요.");
        continue;
      }
    }
    
    song.setLike((prompt.inputString("이 노래를 좋아하십니까?(Y/n) ").equals("n") ? UNLIKE : LIKE));
    
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
    song.setGenre(genre);
    this.list.add(song);
    System.out.printf("%d : %s의 노래 %s(이)가 등록되었습니다!\n", song.getId(), song.getSinger(), song.getTitle());
  }
  
}
