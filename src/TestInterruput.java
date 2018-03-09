import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 中断
 * @Author TAO
 * @Date 2018/2/9 14:33
 */
public class TestInterruput extends Thread{

    private final BlockingQueue<BigInteger> queue;

    public TestInterruput(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        System.out.println(Thread.currentThread().getId()+"run");
        while (!Thread.currentThread().isInterrupted()){
            p = p.nextProbablePrime();
            try {
                queue.put(p);
            } catch (InterruptedException e) {
                /*允许线程退出*/
                System.out.println("stop");
                Thread.currentThread().stop();
            }
        }
    }

    public void cancle(){
        System.out.println(Thread.currentThread().getId()+"cancle");
        interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> bigIntegers = new ArrayBlockingQueue<BigInteger>(30);
        TestInterruput testInterruput = new TestInterruput(bigIntegers);
        testInterruput.start();
        TimeUnit.SECONDS.sleep(1);
        testInterruput.cancle();
        while (!bigIntegers.isEmpty()){
            System.out.println(bigIntegers.take());
        }
    }

}
