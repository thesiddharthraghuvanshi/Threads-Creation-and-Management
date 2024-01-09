import java.util.concurrent.locks.StampedLock;

public class SharedResourceStampedLock {
  int a = 10;
  StampedLock lock = new StampedLock();

  public void produce() {
    long stamp = lock.tryOptimisticRead();
    try {
      System.out.println("Taken Optimistic lock");
      a=11;
      Thread.sleep(6000);
      if(lock.validate(stamp)) {
        System.out.println("Updated value successfully");
      } else {
        System.out.println("Rollback of work");
        a=10;
      }
    } catch(Exception e){
      //throw some exception
    }
  }

  public void consume() {
    long stamp = lock.writeLock();
    System.out.println("Write lock acquired by : " + Thread.currentThread().getName());

    try{
      System.out.println("Performing work");
      a=9;
    } finally {
      lock.unlockWrite(stamp);
      System.out.println("Write lock released by : " + Thread.currentThread().getName());
    }
  }
}