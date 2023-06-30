package bitcamp.myapp.Listener;

import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.vo.Song;

import java.util.List;

public class SongListListener extends AbstractSongListener {
  
  public SongListListener(List<Song> list) {
    super(list);
  }
  
  @Override
  public void service(BreadCrumbPrompt prompt) {
    
    for (int i = 0; i < this.list.size(); i++) {
      Song s = (Song) this.list.get(i);
      if (s == null) {
        System.out.println("없는 노래입니다.");
      }
      
      if (!s.isLike()) {
        System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", s.getId(), s.getTitle(), s.getSinger(), s.getAlbum(), s.getGenre(), s.getYear(), "싫어요");
      } else {
        System.out.printf("%d번 노래 : %s - %s / %s / %s, %d년에 발매됨. %s\n", s.getId(), s.getTitle(), s.getSinger(), s.getAlbum(), s.getGenre(), s.getYear(), "좋아요");
      }
    }
    
  }
  
}
