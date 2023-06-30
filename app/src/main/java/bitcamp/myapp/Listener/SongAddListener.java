package bitcamp.myapp.Listener;

import bitcamp.myapp.util.BreadCrumbPrompt;
import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.Song;

public class SongAddListener extends AbstractSongListener{
  
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
    song.setGenre(inputGenre(song, prompt));
    song.setLike((prompt.inputString("이 노래를 좋아하십니까?(Y/n) ").equals("n") ? UNLIKE : LIKE));
    this.list.add(song);
    System.out.printf("%d : %s의 노래 %s(이)가 등록되었습니다!\n", song.getId(), song.getSinger(), song.getTitle());
  }
  
}
