import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author TAO
 * @Date 2018/3/2 17:32
 */
public class TestCyclicBarrier2 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3,new BarrierTime());

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
        new Thread(()->{
            try {

                cyclicBarrier.await();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        System.out.println("1");
        cyclicBarrier.await();
        System.out.println("2");
        Thread.sleep(1000);
        cyclicBarrier.await();
        System.out.println("3");
    }

    public static class BarrierTime implements Runnable{

        private boolean started;
        private long starttime,endtime;

        //每满足一次条件就运行一次
        @Override
        public synchronized void run() {
            long t = System.nanoTime();
            if(!started){
                System.out.println("started");
                started = true;
                starttime = t;
            }else {
                System.out.println("end");
                endtime = t;
            }
        }

        public synchronized void clear(){
            started = false;
        }

        public synchronized long getTime(){
            return endtime - starttime;
        }
    }
}
