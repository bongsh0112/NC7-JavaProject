package bitcamp.myapp.Listener;

import bitcamp.myapp.util.ActionListener;
import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.Song;

public abstract class AbstractSongListener implements ActionListener {

  protected List list;
  
  protected static final String POP = "팝";
  protected static final String ROCK = "락";
  protected static final String ELECTRONIC = "EDM";
  protected static final String BALLAD = "발라드";
  protected static final String DANCE = "댄스";
  protected static final String HIPHOP = "힙합";
  protected static final boolean LIKE = true;
  protected static final boolean UNLIKE = false;

  public AbstractSongListener(List list) {
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
      label = "이 노래의 장르는 무엇입니까? (팝, 락, EDM, 발라드, 댄스, 힙합)";
    } else {
      label = String.format("%s -> (팝, 락, EDM, 발라드, 댄스, 힙합 중 1)", song.getGenre());
    }
    
    while (true) {
      String genre = prompt.inputString(label);
      
      switch (genre) {
        case POP -> {
          return POP;
        }
        case ROCK -> {
          return ROCK;
        }
        case ELECTRONIC -> {
          return ELECTRONIC;
        }
        case BALLAD -> {
          return BALLAD;
        }
        case DANCE -> {
          return DANCE;
        }
        case HIPHOP -> {
          return HIPHOP;
        }
        default -> System.out.println("등록되지 않은 장르입니다.");
      }
    }
  }
  
}
