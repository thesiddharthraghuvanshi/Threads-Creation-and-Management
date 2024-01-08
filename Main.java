// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

class Main {
  public static void main(String[] args) {
    //SharedResourceOfVariableExample();
    //SharedResourceOfQueueExample();
  }

  private static void SharedResourceOfVariableExample() {
    SharedResourceOfVariable sharedResource = 
                                      new SharedResourceOfVariable();

    Thread producerThread = new Thread(() -> {
      try {
        Thread.sleep(3000l);
      } catch (Exception e) {
        // throw some exception
      }
      sharedResource.addItem();
    });

    Thread consumerThread = new Thread(() -> {
      sharedResource.consumeItem();
    });

    producerThread.start();
    consumerThread.start();
  }

  private static void SharedResourceOfQueueExample() {
    SharedResourceOfQueue sharedResource = 
                                      new SharedResourceOfQueue(3);
    
    Thread producerThread = new Thread(() -> {
      try {
        for(int i = 0; i <=6; i++) {
          sharedResource.produce(i);
        }
      } catch (Exception e) {
        // throw some exception
      }
    });

    Thread consumerThread = new Thread(() -> {
      try {
        for(int i = 0; i <=6; i++) {
          sharedResource.consume();
        }
      } catch (Exception e) {
        // throw some exception
      }
    });

    producerThread.start();
    consumerThread.start();
  }

  private static void sharedResourceDeadLockExample() {
    SharedResource resource = new SharedResource();
    System.out.println("Main Thread started");

    Thread th1 = new Thread(() -> {
      System.out.println("Thread1 calling produce method");
      resource.produce();
    });

    Thread th2 = new Thread(() -> {
      try{
        Thread.sleep(1000);
      } catch(Exception e) {
        //throw some exception
      }

      System.out.println("Thread2 calling produce method");
      resource.produce();
    });

    th1.start();
    th2.start();

    try {
      Thread.sleep(3000);
    } catch(Exception e) {
      //throw some exception
    }

    System.out.println("Thread1 is suspended");
    th1.suspend();

    System.out.println("Main Thread is finishing it's work");
  }

  // @Test
  // void addition() {
  // assertEquals(2, 1 + 1);
  // }
}