import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 在外部线程中安排中断 （不要这么做）
 * @Author TAO
 * @Date 2018/2/9 17:41
 */
public class TestScheduled {

    private static final ScheduledExecutorService cancleService = Executors.newScheduledThreadPool(1);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit){
        final Thread taskThread = Thread.currentThread();
        System.out.println(taskThread.getId()+"task");
        cancleService.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        },timeout,unit);
//        r.run(); //当前线程依旧是主线程 -> 方法调用者
        new Thread(r).start(); //当前线程为子线程
    }

    public static void main(String[] args) {
        Runnable r = new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId()+"run");

                while (!Thread.currentThread().isInterrupted()){
                }
            }
        };
        TestScheduled.timedRun(r,1,TimeUnit.SECONDS);
    }
}
