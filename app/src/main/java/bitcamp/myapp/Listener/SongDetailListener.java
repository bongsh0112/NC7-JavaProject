package bitcamp.myapp.Listener;

import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.Song;

public class SongDetailListener extends AbstractSongListener {
  
  public SongDetailListener(List list) {
    super(list);
  }
  
  @Override
  public void service(BreadCrumbPrompt prompt) {
    int songId = Integer.parseInt(prompt.inputString("노래 번호? "));
    
    Song s = this.findBy(songId);
    if (s == null) {
      System.out.printf("%d번의 번호를 가진 노래는 없습니다!%n", songId);
      return;
    }
    
    if (!s.isLike()) {
      System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", s.getId(), s.getTitle(), s.getSinger(), s.getAlbum(), s.getGenre(), s.getYear(), "싫어요");
    } else {
      System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", s.getId(), s.getTitle(), s.getSinger(), s.getAlbum(), s.getGenre(), s.getYear(), "좋아요");
    }
  }
}
