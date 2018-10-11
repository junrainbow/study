package jun.liu.redis.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-01 20:35
 */
public class DistributedLockTest implements Runnable{

    private static final int LOOP = 100000;

    private static int count = 0;

    private static final String LOCK_NAME = "jun:lock";

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1,100,1, TimeUnit.SECONDS, new LinkedBlockingQueue());

    private static CountDownLatch cdl = new CountDownLatch(LOOP);

    public static void main(String[] args) throws Exception {
        DistributedLockTest task = new DistributedLockTest();
        for (int i = 0; i < LOOP; i++) {
            threadPool.submit(task);
        }
        //优雅关闭
        threadPool.shutdown();
        //等待执行完毕
        cdl.await();
        //打印结果
        System.out.println("计算结果：   "+count);
    }


    public void run() {
        while (true){
            //获得锁
            boolean get = DistributedLock.tryLock(LOCK_NAME, 60);
            if(get){
                break;
            }
        }
        //执行临界资源
        count++;
        //释放锁
        while (true){
            boolean release = DistributedLock.tryRelease(LOCK_NAME);
            if(release){
                break;
            }
        }
        cdl.countDown();
    }
}
