package bitcamp.myapp.util;

public class Queue extends LinkedList {
  
  public void offer(Object object) {
    // 목록 맨 끝에 추가한다.
    // 따로 만들 필요가 없다.
    // 수퍼 클래스에 있는 메서드를 이용하여 기능을 구현한다.
    this.add(object); // 상속 받은 메서드 = 서브 클래스에서 사용할 수 있는 수퍼 클래스의 메서드
  }
  
  public Object poll() {
    if (this.size() == 0) {
      return null;
    }
    return this.remove(0);
  }
  
  
  
}
