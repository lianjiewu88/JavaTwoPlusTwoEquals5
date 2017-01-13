package semaphore;

public class Philosopher implements Runnable {
    private int index;      // 编号
    private String name;    // 名字
 
    public Philosopher(int index, String name) {
        this.index = index;
        this.name = name;
    }
 
    @Override
    public void run() {
        while(true) {
            try {
                AppContext.counter.acquire();
                boolean leftFirst = index % 2 == 0;
                AppContext.putOnFork(index, leftFirst);
                System.out.println(name + "正在吃意大利面（通心粉）...");   // 取到两个叉子就可以进食
                AppContext.putDownFork(index, leftFirst);
                AppContext.counter.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}