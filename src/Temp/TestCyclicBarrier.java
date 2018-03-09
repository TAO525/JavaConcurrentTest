package Temp;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author TAO
 * @Date 2018/3/2 17:32
 */
public class TestCyclicBarrier {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        new Thread(()->{
            try {
                Thread.sleep(1000);
                cyclicBarrier.await();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        cyclicBarrier.await();
        cyclicBarrier.await();
        System.out.println(1);
    }
}
