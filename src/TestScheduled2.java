import java.security.PublicKey;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 在外部线程中安排中断 （不要这么做）
 * @Author TAO
 * @Date 2018/2/9 17:41
 */
public class TestScheduled2 {

    private static final ScheduledExecutorService cancleService = Executors.newScheduledThreadPool(1);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class ReThrowTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();

                } catch (Throwable t) {
                    this.t = t;
                }
            }

            public void rethrow() {
                if (t != null) {
                    System.out.println("aaa");
                }else {
                    System.out.println("bbb");
                }
            }
        }
        ReThrowTask reThrowTask = new ReThrowTask();
        final Thread thread = new Thread(reThrowTask);
        thread.start();
        cancleService.schedule(()->
          thread.interrupt()
        ,timeout,unit);

        thread.join(unit.toMillis(timeout));
        reThrowTask.rethrow();
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId()+"run");

                while (!Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        };
        TestScheduled2.timedRun(r,5,TimeUnit.SECONDS);
        cancleService.shutdown();//不加这句 主线程会持续下去
    }
}
