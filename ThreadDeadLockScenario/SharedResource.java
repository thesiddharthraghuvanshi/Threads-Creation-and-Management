public class SharedResource {
  boolean isAvailabe = false;

  public synchronized void produce(){
    System.out.println("Lock Acquried");
    isAvailabe = true;
    try{
      Thread.sleep(7000);
    } catch(Exception e){
      //throw some exception
    }

    System.out.println("Lock Released");
  }
}