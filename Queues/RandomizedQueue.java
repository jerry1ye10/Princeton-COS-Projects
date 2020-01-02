import java.io.*;
import java.util.*;
import java.util.Random;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item> {
    public ArrayList<Item> data;
    public int size;
    // construct an empty randomized queue
    public RandomizedQueue(){
      data = new ArrayList<Item>();
      size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
      return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
      return size;
    }

    // add the item
    public void enqueue(Item item){
      if (item == null){
        throw new IllegalArgumentException();
      }
      data.add(item);
      size += 1;
    }

    // remove and return a random item
    public Item dequeue(){
      if (size == 0){
        throw new NoSuchElementException();
      }
      size -= 1;
      Random rand = new Random();
      int random = rand.nextInt(data.size());
      Item retItem = data.get(random);
      data.remove(random);
      return retItem;

    }

    // return a random item (but do not remove it)
    public Item sample(){
      if (size == 0){
        throw new NoSuchElementException();
      }
      Random rand = new Random();
      return data.get(rand.nextInt(data.size()));
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
      return new RQIterator<Item>();
    }
    public class RQIterator<Item> implements Iterator<Item>{
      public ArrayList<Item> dataCopy;
      public RQIterator(){
        dataCopy = (ArrayList<Item>) data.clone();
      }
      public boolean hasNext(){
        return dataCopy.size() != 0;
      }
      public Item next(){
        if (dataCopy.size() == 0){
          throw new NoSuchElementException();
        }
        Random rand = new Random();
        int random = rand.nextInt(dataCopy.size());
        Item retItem = dataCopy.get(random);
        dataCopy.remove(random);
        return retItem;
      }
      public void remove(){
        throw new UnsupportedOperationException();
      }
    }
    public String toString(){
      return data.toString();
    }

    // unit testing (required)
    public static void main(String[] args){
      RandomizedQueue deq = new RandomizedQueue<Integer>();
      deq.enqueue(1);
      deq.enqueue(2);
      deq.enqueue(3);
      Iterator<Integer> iter = deq.iterator();
      while (iter.hasNext()){
        System.out.println(iter.next());
      }
      System.out.println(deq);
      deq.dequeue();
      System.out.println(deq);
      deq.dequeue();
      System.out.println(deq);
      deq.dequeue();
      System.out.println(deq);

    }

}
