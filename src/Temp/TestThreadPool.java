package Temp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author TAO
 * @Date 2018/3/19 10:44
 * awaitTermination不会触发java.util.concurrent.RejectedExecutionException
 * shutdown会
 */
public class TestThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(30);
                    System.out.println(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();
        while (!executorService.awaitTermination(6,TimeUnit.SECONDS)){
            System.out.println(3);
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
            }
        });
    }
}
