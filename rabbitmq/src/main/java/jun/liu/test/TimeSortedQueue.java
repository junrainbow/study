package jun.liu.test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @ClassName TimeSortedQueue
 * @Description <h1>比如说现有十个任务,分别要求等待0,1,2...9 秒后进行执行</h1>
 * <p>从9开始向队列中放置任务,之后要求输出0,1,2,3....9</p>
 * @Author MoreRoom
 * @Since 2018/8/30
 */
public class TimeSortedQueue {

    public static void main(String[] args) throws InterruptedException {
        // 消费者线程池
        ExecutorService poolExecutor = new ThreadPoolExecutor(0, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));
        DelayQueue<TimeDelay> taskQueue = new DelayQueue<TimeDelay>();
        // 1. 生产线程启动
        new Thread(new ProducerThread(taskQueue)).start();
        // 2. 消费线程启动
        while (true) {
            if (taskQueue != null) {
                TimeDelay result = taskQueue.poll();
                if (result != null) {
                    poolExecutor.execute(new Thread(new CustomerThread(result)));
                }
            }
            if (taskQueue.size() == 0 && ((ThreadPoolExecutor) poolExecutor).getCompletedTaskCount() == 10000) {
                poolExecutor.shutdown();
                break;
            }
        }
    }

    /**
     * 想要实现延迟队列的效果,需要实现Delayed接口
     */
    private static class TimeDelay implements Delayed {
        /**
         * 执行时间戳
         */
        private long executeTimestamp;

        private int waitSeconds;

        public TimeDelay(int executeTime) {
            waitSeconds = executeTime / 1000;
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(new Date());
            currentCalendar.add(Calendar.MILLISECOND, executeTime);
            executeTimestamp = currentCalendar.getTime().getTime();
        }

        public long showExecuteTimestamp() {
            return executeTimestamp;
        }

        public int getWaitSeconds() {
            return waitSeconds;
        }

        /**
         * 返回剩余的延迟时间
         * 毫秒为单位
         *
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(executeTimestamp - System.currentTimeMillis(), unit);
        }

        /**
         * 用于比较,实现内部的排序规则
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            if (o instanceof TimeDelay) {
                int result = (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
                if (result == 0) {
                    return 0;
                } else if (result > 0) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                throw new IllegalArgumentException("argument is not instance of TimeDelay.");
            }
        }
    }

    /**
     * 任务生产线程  要求生产10000个线程任务
     */
    private static class ProducerThread implements Runnable {

        private static final int TASK_NUMBER = 10000;

        private DelayQueue<TimeDelay> taskQueue;

        public ProducerThread(DelayQueue delayQueue) {
            this.taskQueue = delayQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < TASK_NUMBER; i++) {
                int waitTime = (int) (Math.random() * 100 / 10);
                taskQueue.add(new TimeDelay(waitTime * 1000));
            }
        }
    }


    /**
     * 消费线程
     */
    private static class CustomerThread implements Runnable {

        private TimeDelay timeDelay;

        public CustomerThread(TimeDelay timeDelay) {
            this.timeDelay = timeDelay;
        }

        @Override
        public void run() {
            if (null == timeDelay) {
                return;
            }
            System.out.println("此任务已经等待" + timeDelay.getWaitSeconds() + "s,现在执行.");
        }

    }


}
