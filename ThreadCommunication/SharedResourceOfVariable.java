public class SharedResourceOfVariable {
  boolean isItemPresent = false;

  public synchronized void addItem() {
    this.isItemPresent = true;
    System.out.println("producerThread calling the notify method");
    notifyAll();
  }

  public synchronized void consumeItem() {
    System.out.println("consumerThread inside consumeItem method");
    
    while(!isItemPresent) {
      try {
        System.out.println("consumerThread is waiting");
        wait(); //releases all the monitor locks here
      } catch(Exception e) {
        //throw some exception
      }
    }
    this.isItemPresent = false;
    System.out.println("consumerThread leaving consumeItem method");
  }
}