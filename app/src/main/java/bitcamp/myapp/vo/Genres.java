package bitcamp.myapp.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Genres {
  POP("팝", "pop"),
  ROCK("락", "rock"),
  ELECTRONIC("EDM", "edm"),
  BALLAD("발라드", "ballad"),
  DANCE("댄스", "dance"),
  HIPHOP("힙합", "hiphop");
  
  private final String kor;
  private final String eng;
  
  public static String getGenreToKorStringByString(String genres) {
    Genres result = Arrays.stream(Genres.values()).filter(type -> type.getKor().equals(genres.trim()) || type.getEng().equalsIgnoreCase(genres.trim())).findFirst().orElse(null);
    return result == null ? null : result.getKor();
  }
  
}