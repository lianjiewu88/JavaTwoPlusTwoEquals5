package lock;

public class ReaderThread extends Thread {
	 
    private final DataForLock data;
 
    public ReaderThread(DataForLock data) {
        this.data = data;
    }
 
    @Override
    public void run() {
        while (true) {
            String result = data.read();
            System.out.println("Reader thread: " + Thread.currentThread().getName() + " read result " + result);
        }
    }
}