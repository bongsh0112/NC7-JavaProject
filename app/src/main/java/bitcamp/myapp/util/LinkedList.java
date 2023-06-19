package bitcamp.myapp.util;

public class LinkedList implements List {

  int size;
  private final int DEFAULT_SIZE = 100;
  private Object[] list = new Object[DEFAULT_SIZE];

  Node head;
  Node tail;

  static class Node {

    Object value;
    Node next;

  }

  @Override
  public boolean add(Object value) {
    // 새 노드 만들기
    Node node = new Node();
    node.value = value;
    node.next = null;

    if (head == null) {
      // 맨 앞 노드가 비어있다면
      head = node;
    } else if (this.tail != null) {
      // tail이 있다면. 즉 처음과 끝이 설정되었다면
      this.tail.next = node;
    }

    this.tail = node;
    this.size++;
    return true;
  }

  @Override
  public Object get(int index) {
    if (!isValid(index)) {
      return null;
    }
    
    Node cursor = this.head;
    
    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }

    return cursor.value;
  }

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[size];
    
    Node cursor = this.head;
    for (int i = 0; i < size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }

    return arr;
  }


  @Override
  public Object remove(int index) { // 번호를 입력하여 삭제할 것이므로
    
    if (!isValid(index)) {
      return null;
    }
    
    Node prev = null;
    Node cursor = this.head;
    
    for (int i = 0; i < index; i++) { // OutOfIndex는 일어나지 않을 것
      prev = cursor;
      cursor = cursor.next;
    }
    
    Object old = cursor.value;
    
    if (prev == null) {
      // 맨 처음 노드를 지울 때
      head = cursor.next;
      if (head == null) { // 맨 처음이자 마지막 노드를 지우기 때문에 위의 cursor.next가 null이라 head가 null이 된 것.
        // 맨 처음이자 마지막 노드를 지울 때
        tail = null;
      }
    } else if (cursor.next == null) {
      // 맨 마지막 노드를 지울 때
      tail = prev;
      tail.next = null;
    } else {
      // 일반적인 삭제
      prev.next = cursor.next;
    }
    size--;
    cursor.next = null;
    cursor.value = null;

    return old;
  }


  @Override
  public boolean remove(Object value) {
    Node prev = null;
    Node cursor = head;

    while (cursor != null) {

      if (cursor.value.equals(value)) {
        if (prev == null) {
          // 삭제하고자 하는 노드가 head일 때
          head = cursor.next;
          
          if (head == null) { // 맨 처음이자 마지막 노드를 지우기 때문에 위의 cursor.next가 null이라 head가 null이 된 것.
            // 삭제하고자 하는 노드가 시작이자 끝 노드라면
            tail = null;
          }
          
        } else if (cursor.next == null) {
          // 삭제하고자 하는 노드가 tail일 때
          tail = prev;
          tail.next = null;
        } else {
          // 일반적인 삭제
          prev.next = cursor.next;
        }
        size--;
        
        cursor.next = null;
        cursor.value = null;
        
        return true;
      }

      prev = cursor;
      cursor = cursor.next;
    }
    
    return false;

  }

  @Override
  public int size() {
    return this.size;
  }

  private boolean isValid(int index) {
    if (index < 0 || index > this.size) {
      return false;
    }
    return true;
  }
  
}
