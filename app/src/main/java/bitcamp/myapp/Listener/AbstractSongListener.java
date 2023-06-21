package bitcamp.myapp.Listener;

import bitcamp.myapp.util.ActionListener;
import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.Song;

public abstract class AbstractSongListener implements ActionListener {

  protected List list;

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
  
}
