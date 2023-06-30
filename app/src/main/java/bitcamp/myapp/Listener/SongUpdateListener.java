package bitcamp.myapp.Listener;

import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.Song;

public class SongUpdateListener extends AbstractSongListener {
  public SongUpdateListener(List list) {
    super(list);
  }
  
  @Override
  public void service(BreadCrumbPrompt prompt) {
    int songId = Integer.parseInt(prompt.inputString("노래 번호? "));
    Song s = findBy(songId);
    if (s == null) {
      System.out.printf("%d번의 번호를 가진 노래는 없습니다!%n", songId);
      return;
    }
    
    if (!s.isLike()) {
      System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", s.getId(), s.getTitle(), s.getSinger(), s.getAlbum(), s.getGenre(), s.getYear(), "싫어요");
    } else {
      System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", s.getId(), s.getTitle(), s.getSinger(), s.getAlbum(), s.getGenre(), s.getYear(), "좋아요");
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
        s.setGenre(inputGenre(s, prompt));
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
    }
  }
  
}
