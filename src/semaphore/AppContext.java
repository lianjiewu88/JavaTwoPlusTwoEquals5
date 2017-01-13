package semaphore;

//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 存放线程共享信号量的context
 *
 */
class AppContext {
	public static final int NUM_OF_FORKS = 5; // 叉子数量(资源)
	public static final int NUM_OF_PHILO = 5; // 哲学家数量(线程)

	public static Semaphore[] forks; // 叉子的信号量
	public static Semaphore counter; // 哲学家的信号量

	static {
		forks = new Semaphore[NUM_OF_FORKS];

		for (int i = 0, len = forks.length; i < len; ++i) {
			forks[i] = new Semaphore(1); // 每个叉子的信号量为1
		}

		counter = new Semaphore(NUM_OF_PHILO - 1); // 如果有N个哲学家，最多只允许N-1人同时取叉子
	}

	/**
	 * 取得叉子
	 * 
	 * @param index
	 *            第几个哲学家
	 * @param leftFirst
	 *            是否先取得左边的叉子
	 * @throws InterruptedException
	 */
	public static void putOnFork(int index, boolean leftFirst)
			throws InterruptedException {
		if (leftFirst) {
			forks[index].acquire();
			forks[(index + 1) % NUM_OF_PHILO].acquire();
		} else {
			forks[(index + 1) % NUM_OF_PHILO].acquire();
			forks[index].acquire();
		}
	}

	/**
	 * 放回叉子
	 * 
	 * @param index
	 *            第几个哲学家
	 * @param leftFirst
	 *            是否先放回左边的叉子
	 * @throws InterruptedException
	 */
	public static void putDownFork(int index, boolean leftFirst)
			throws InterruptedException {
		if (leftFirst) {
			forks[index].release();
			forks[(index + 1) % NUM_OF_PHILO].release();
		} else {
			forks[(index + 1) % NUM_OF_PHILO].release();
			forks[index].release();
		}
	}
	
	public static void main(String[] args) {
        String[] names = { "骆昊", "王大锤", "张三丰", "杨过", "李莫愁" };   // 5位哲学家的名字
//      ExecutorService es = Executors.newFixedThreadPool(AppContext.NUM_OF_PHILO); // 创建固定大小的线程池
//      for(int i = 0, len = names.length; i < len; ++i) {
//          es.execute(new Philosopher(i, names[i]));   // 启动线程
//      }
//      es.shutdown();
        for(int i = 0, len = names.length; i < len; ++i) {
            new Thread(new Philosopher(i, names[i])).start();
        }
    }
	
}
