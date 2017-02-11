package lock;

public class LockTest {
	 
    public static void main(String[] args) {
        DataForLock data = new DataForLock(10);
 
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
 
        new WriterThread(data, "ABCDEFGHI").start();
        new WriterThread(data, "012345789").start();
    }
}