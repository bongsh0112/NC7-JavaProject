package bitcamp.myapp.Listener;

import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.vo.Song;

import java.util.List;

public class SongDeleteListener extends AbstractSongListener {
  
  public SongDeleteListener(List<Song> list) {
    super(list);
  }
  
  @Override
  public void service(BreadCrumbPrompt prompt) {
    int songId = prompt.inputInt("삭제할 노래의 번호? ");
    if (!list.remove(findBy(songId))) {
      System.out.println("해당 번호의 노래가 없습니다!");
    }
  }
}
