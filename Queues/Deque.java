import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item>{

public LinkedList<Item> data;
public int size;
public Deque(){
  data = new LinkedList<Item>();
  size = 0;
}

public boolean isEmpty(){
  return size == 0;
}

public int size(){
  return size;
}

public void addFirst(Item item){
  data.addFirst(item);
  size += 1;
}

public void addLast(Item item){
  if (item == null){
    throw new IllegalArgumentException("Input cannot be null");
  }
  data.addLast(item);
  size += 1;
}

public Item removeFirst(){
  if (size == 0) {
    throw new NoSuchElementException("Data must have elements inside.");
  }
  size -= 1;
  return data.removeFirst();
}

public Item removeLast(){
  if (size == 0) {
    throw new NoSuchElementException("Data must have elements inside.");
  }
  size += 1;
  return data.removeLast();
}

public Iterator<Item> iterator(){
  return new DequeIterator<Item>();
}

public class DequeIterator<Item> implements Iterator<Item>{
  public int cursor;
  public DequeIterator(){
    cursor = 0;
  }
  public boolean hasNext(){
    return cursor < size;
  }
  public Item next(){
    if (cursor == size) {
      throw new NoSuchElementException("Data must have elements inside.");
    }
    cursor += 1;
    return (Item) data.get(cursor - 1);
  }
  public void remove(){
    throw new UnsupportedOperationException();
  }
}
public String toString(){
  return data.toString();
}


public static void main(String args[]){
  Deque deq = new Deque<Integer>();
  deq.addFirst(1);
  deq.addFirst(2);
  deq.addLast(3);
  Iterator<Integer> iter = deq.iterator();
  while (iter.hasNext()){
    System.out.println(iter.next());
  }
  System.out.println(deq);
  deq.removeLast();
  System.out.println(deq);
  deq.removeFirst();
  System.out.println(deq);

}

}
