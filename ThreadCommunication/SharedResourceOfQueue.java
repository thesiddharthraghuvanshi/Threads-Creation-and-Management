import java.util.*;

public class SharedResourceOfQueue {
  private Queue<Integer> sharedQueue;
  private int size;

  public SharedResourceOfQueue(int size) {
    sharedQueue = new LinkedList<>();
    this.size = size;
  }

  public synchronized void produce(int item) throws Exception {
    while (sharedQueue.size() == size) {
      System.out.println("Queue is full. Waiting for consumer to consume...");
      wait();
    }
    sharedQueue.add(item);
    System.out.println("Produced: " + item);
    notifyAll();
    notify();
  }

  public synchronized int consume() throws Exception {
    while (sharedQueue.isEmpty()) {
      System.out.println("Queue is empty. Waiting for producer to produce...");
      wait();
    }
    int item = sharedQueue.poll();
    notify();
    return item;
  }
}