package bitcamp.myapp.SongHandler;

public class ArrayList {

  private final int DEFAULT_SIZE = 100;

  public static int length = 0;
  private Object[] list = new Object[DEFAULT_SIZE];

  public boolean add(Object object) {
    if (this.length == list.length) {
      increase();
    }
    this.list[length++] = object;
    return true;
  }

  public void increase() {
    Object[] arr = new Object[DEFAULT_SIZE * 2];
    for (int i = 0; i < length; i++) {
      arr[i] = list[i];
    }
    list = arr;
  }

  public Object[] list() {
    Object[] tempArr = new Object[length];
    for (int i = 0; i < length; i++) {
      tempArr[i] = this.list[i];
    }
    return tempArr;
  }

  public Object get(Object object) {
    for (int i = 0; i < list.length; i++) {
      Object item = this.list[i];
      if (item.equals(object)) {
        return item;
      }
    }
    return null;
  }

  public int indexOf(Object object) {
    for (int i = 0; i < length; i++) {
      Object obj = this.list[i];
      if (obj.equals(object)) {
        return i;
      }
    }
    return -1;
  }

  public boolean delete(Object object) {
    int deletedIndex = indexOf(object);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      this.list[i] = this.list[i + 1];
    }

    list[length] = null;
    length--;
    return true;
  }
}
