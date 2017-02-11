package lock;
/*
 * result without synchronized in write and read of DataForLock:
 * Reader thread: Thread-3 read result **********
Reader thread: Thread-0 read result **********
Reader thread: Thread-4 read result **********
Reader thread: Thread-2 read result **********
Reader thread: Thread-1 read result **********
Reader thread: Thread-3 read result 00A*******
Reader thread: Thread-0 read result 000*******
Reader thread: Thread-4 read result 000*******
Reader thread: Thread-2 read result 000*******
Reader thread: Thread-1 read result 000*******
Reader thread: Thread-4 read result 00000*****
Reader thread: Thread-0 read result 00000*****
Reader thread: Thread-3 read result 00000*****
Reader thread: Thread-2 read result 00000*****
Reader thread: Thread-1 read result 00000*****
Reader thread: Thread-0 read result 000000A***
Reader thread: Thread-4 read result 000000A***
Reader thread: Thread-3 read result 0000000***
Reader thread: Thread-1 read result 0000000***
Reader thread: Thread-2 read result 0000000***
Reader thread: Thread-4 read result 00000000**
Reader thread: Thread-0 read result 00000000**
Reader thread: Thread-1 read result 00000000A*
Reader thread: Thread-2 read result 00000000A*
Reader thread: Thread-3 read result 00000000A*
Reader thread: Thread-0 read result 0000000000
Reader thread: Thread-4 read result 0000000000
Reader thread: Thread-3 read result 0000000000
Reader thread: Thread-1 read result 0000000000
Reader thread: Thread-2 read result 0000000000
Reader thread: Thread-0 read result 1100000000
Reader thread: Thread-4 read result 1100000000
Reader thread: Thread-3 read result 1100000000
Reader thread: Thread-2 read result 1100000000
Reader thread: Thread-1 read result 1100000000
Reader thread: Thread-4 read result 1111000000
Reader thread: Thread-0 read result 1111000000
Reader thread: Thread-2 read result 1111000000
Reader thread: Thread-1 read result 1111000000
Reader thread: Thread-3 read result 1111000000
Reader thread: Thread-4 read result 1111110000
Reader thread: Thread-0 read result 1111110000
Reader thread: Thread-3 read result 1111110000
Reader thread: Thread-2 read result 1111110000
Reader thread: Thread-1 read result 1111110000
Reader thread: Thread-4 read result 1111111100
Reader thread: Thread-0 read result 1111111100
Reader thread: Thread-1 read result 1111111100
Reader thread: Thread-3 read result 1111111100
Reader thread: Thread-2 read result 1111111100
Reader thread: Thread-0 read result 111111111B
Reader thread: Thread-4 read result 111111111B
Reader thread: Thread-3 read result 111111111B
Reader thread: Thread-1 read result 111111111B
Reader thread: Thread-2 read result 111111111B
Reader thread: Thread-0 read result CC1111111B
Reader thread: Thread-4 read result CC1111111B
Reader thread: Thread-2 read result CC1111111B
Reader thread: Thread-1 read result CC1111111B
Reader thread: Thread-3 read result CC1111111B
 */
public class LockTest {
	 
    public static void main(String[] args) {
        DataForLock data = new DataForLock(10);
 
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
 
        // every char is written, then sleep 500 ms
        new WriterThread(data, "ABCDEFGHI").start();
        // new WriterThread(data, "012345789").start();
    }
}