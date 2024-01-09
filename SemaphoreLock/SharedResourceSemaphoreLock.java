import java.util.concurrent.Semaphore;

public class SharedResourceSemaphoreLock {
  boolean isAvaiable = false;
  Semaphore lock = new Semaphore(2);

  public void producer(){

    try{
      lock.acquire();
      System.out.println("Lock Acquired vy : " + Thread.currentThread().getName());
      isAvaiable = true;
      Thread.sleep(4000);
    } catch(Exception e) {
      //throw some exception
    } finally {
      lock.release();
      System.out.println("Lock Released by : " + Thread.currentThread().getName());
    }
  }
}