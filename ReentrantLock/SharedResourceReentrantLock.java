import java.util.concurrent.locks.ReentrantLock;

public class SharedResourceReentrantLock {
  boolean isAvailable = false;

  public void producer(ReentrantLock lock){
    System.out.println("Inside producer");
    try{
      lock.lock();
      System.out.println("Lock acquired by : "+ Thread.currentThread().getName());
      isAvailable = true;
      Thread.sleep(4000);
    } catch(Exception e){
      //throw some exception
    } finally {
      lock.unlock();
      System.out.println("Lock released by : "+ Thread.currentThread().getName());
    }
  }
}