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
    if (!this.list.remove(new Song(prompt.inputInt("삭제할 노래의 번호를 입력해주세요. ")))) {
      System.out.println("해당 번호의 노래가 없습니다!");
    }
  }
}
