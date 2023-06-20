package bitcamp.myapp.util;

public class Stack extends LinkedList {
  
  public void push(Object object) {
    this.add(object);
  }
  
  public Object pop() {
    if (this.size() == 0) {
      return null;
    }
    return this.remove(this.size() - 1);
  }
  
  public Object peek() {
    if (this.size() == 0) {
      return null;
    }
    return this.get(this.size() - 1);
  }
  
  public boolean emtpy() {
    return this.size() == 0;
  }
  
}
