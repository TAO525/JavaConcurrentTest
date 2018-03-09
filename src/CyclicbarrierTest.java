import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 栅栏
 * @Author TAO
 * @Date 2018/2/8 12:02
 */
public class CyclicbarrierTest {


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new Thread(new Runner(cyclicBarrier,"1号选手")));
        executorService.submit(new Thread(new Runner(cyclicBarrier,"2号选手")));
        executorService.submit(new Thread(new Runner(cyclicBarrier,"3号选手")));

        executorService.shutdown();
    }

    /**
     * @Author TAO
     * @Date 2018/2/2 17:57
     */
    static class Runner implements Runnable{

        private CyclicBarrier cyclicBarrier;

        private String name;

        public Runner(CyclicBarrier cyclicBarrier, String name) {
            super();
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000*((new Random()).nextInt(8)));
                System.out.println(name + "准备好了.....");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name+"起跑");
        }
    }

}
