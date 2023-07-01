package bitcamp.myapp.Listener;

import bitcamp.myapp.util.ActionListener;
import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.vo.Genres;
import bitcamp.myapp.vo.Song;

import java.util.List;

public abstract class AbstractSongListener implements ActionListener {

  protected List<Song> list;
  protected static final boolean LIKE = true;
  protected static final boolean UNLIKE = false;

  public AbstractSongListener(List<Song> list) {
    this.list = list;
  }

  protected Song findBy(int id) {
    for (int i = 0; i < this.list.size(); i++) {
      Song s = (Song) this.list.get(i);
      if (s.getId() == id) {
        return s;
      }
    }
    return null;
  }
  
  protected String inputGenre(Song song, BreadCrumbPrompt prompt) {
    String label;
    if (song.getGenre().equals("")) {
      label = "이 노래의 장르는 무엇입니까? (팝, 락, EDM, 발라드, 댄스, 힙합) ";
    } else {
      label = String.format("%s -> (팝, 락, EDM, 발라드, 댄스, 힙합 중 1) ", song.getGenre());
    }
    while (true) {
      String genreKor = Genres.getGenreToKorStringByString(prompt.inputString(label));
      if(genreKor != null) {
        return genreKor;
      }
    }
  }
}
