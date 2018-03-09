import sun.java2d.SurfaceDataProxy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 闭锁
 * @Author TAO
 * @Date 2018/2/2 14:48
 */
public class CountDownLatchTest {
    public static class Task implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("start:"+Thread.currentThread());
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public long timeTasks(int nThread, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThread);

        for (int i = 0; i < nThread; i++) {
            Thread thread = new Thread(){
                public void run(){
                    try {
                        startGate.await();
                        try {
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
        try {
            long l = countDownLatchTest.timeTasks(20, new Task());
            System.out.println(TimeUnit.NANOSECONDS.toSeconds(l));
        } catch (InterruptedException e) {
           Thread.currentThread().interrupted();
        }
    }
}
