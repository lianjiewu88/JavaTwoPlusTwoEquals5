package lock;

public class DataForLock {
	 
    private final char[] buffer;
 
    public DataForLock(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = '*';
        }
    }
 
    //synchronized 
    public String read() {
        StringBuilder result = new StringBuilder();
        for (char c : buffer) {
            result.append(c);
        }
        sleep(500);
        return result.toString();
    }
 
    // synchronized 
    public void write(char c) {
    	System.out.println("Write Thread tries to write buffer with char: " + c);
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            sleep(500);
        }
    }
 
    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
