package bitcamp.myapp.util;

public class ArrayList implements List {

  private final int DEFAULT_SIZE = 100;

  private Object[] list = new Object[DEFAULT_SIZE];
  private int size;

  @Override
  public boolean add(Object object) {
    if (this.size == list.length) {
      increase();
    }
    this.list[size++] = object;
    return true;
  }

  public void increase() {
    Object[] arr = new Object[DEFAULT_SIZE * 2];
    for (int i = 0; i < size; i++) {
      arr[i] = list[i];
    }
    list = arr;
  }

  @Override
  public Object[] toArray() {
    Object[] tempArr = new Object[this.size];
    for (int i = 0; i < this.size; i++) {
      tempArr[i] = this.list[i];
    }
    return tempArr;
  }

  @Override
  public Object get(int index) {
    if (!isValid(index)) {
      return null;
    }
    return this.list[index];
  }

  public int indexOf(Object object) {
    for (int i = 0; i < size; i++) {
      Object obj = this.list[i];
      if (obj.equals(object)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean remove(Object object) {
    int deletedIndex = indexOf(object);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < size - 1; i++) {
      this.list[i] = this.list[i + 1];
    }

    list[size] = null;
    size--;
    return true;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public Object remove(int index) {
    if (!isValid(index)) {
      return null;
    }

    Object obj = this.list[index];

    for (int i = index; i < this.size; i++) {
      list[i] = list[i + 1];
    }
    this.list[--this.size] = null;
    return obj;
  }


  private boolean isValid(int index) {
    return index >= 0 && index < this.size;
  }
}
