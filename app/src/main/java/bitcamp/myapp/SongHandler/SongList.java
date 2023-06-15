package bitcamp.myapp.SongHandler;

import bitcamp.myapp.vo.Song;

public class SongList {

  private final int DEFAULT_SIZE = 100;

  public static int length = 0;
  private Song[] songs = new Song[DEFAULT_SIZE];

  public void add(Song song) {
    if (length == DEFAULT_SIZE) {
      increase();
    }
    songs[length++] = song;
  }

  public void increase() {
    Song[] arr = new Song[DEFAULT_SIZE * 2];
    for (int i = 0; i < length; i++) {
      arr[i] = songs[i];
    }
    songs = arr;
  }

  public Song[] list() {
    Song[] tempArr = new Song[length];
    for (int i = 0; i < length; i++) {
      tempArr[i] = songs[i];
    }
    return tempArr;
  }

  public Song get(int songId) {
    for (int i = 0; i < length; i++) {
      if (songs[i].getId() == songId) {
        return songs[i];
      }
    }
    return null;
  }

  public int indexOf(int songId) {
    for (int i = 0; i < length; i++) {
      Song song = this.songs[i];
      if (song.getId() == songId) {
        return i;
      }
    }
    return -1;
  }

  public boolean delete(int songId) {
    int deletedIndex = indexOf(songId);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      songs[i] = songs[i + 1];
    }

    songs[length] = null;
    length--;
    return true;
  }
}
