import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author TAO
 * @Date 2018/2/27 15:34
 */
public class TestMyAppThread extends Thread{
    public static final String DEFAULT_NAME = "MyAppThread";
    private static volatile boolean debugflag = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getAnonymousLogger();
    private static final AtomicInteger number = new AtomicInteger();

    public TestMyAppThread(Runnable r) {
        this(r,DEFAULT_NAME);
    }

    public TestMyAppThread(Runnable r,String name){
        super(r,name+"-"+created.incrementAndGet());
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.log(Level.SEVERE,"uncatch in thread"+t.getName(),e);
            }
        });
    }

    @Override
    public void run() {
        boolean debug = debugflag;
        if(debug) log.log(Level.INFO,"create"+getName());
        try {
            alive.incrementAndGet();
            number.incrementAndGet();
            super.run();
        }finally {
            alive.decrementAndGet();
            if(debug) log.log(Level.INFO,"exiting"+getName());
        }
    }

    public static int getThreadCreated(){return created.get();}
    public static int getThreadAlive(){return alive.get();}
    public static boolean getDebug(){return debugflag;}
    public static void setDebug(boolean flag){debugflag = flag;}
    public static int getNumber(){return number.get();}

    public static void main(String[] args) throws InterruptedException {
        TestMyAppThread kkk = new TestMyAppThread(()->{
            try {
                while (true) {
                    System.out.println("threadid+++"+Thread.currentThread().getId());
                    TimeUnit.SECONDS.sleep(7);
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }},"kkk");
        TestMyAppThread.setDebug(true);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(kkk);
        executorService.execute(kkk);
        executorService.execute(kkk);//可以重复submit或execute
     /*   executorService.submit(new TestMyAppThread(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }},"yyy"));*/
        System.out.println("alive---"+TestMyAppThread.getThreadAlive());
        System.out.println("create---"+TestMyAppThread.getThreadCreated());
        executorService.shutdown();
        executorService.awaitTermination(3,TimeUnit.SECONDS);
        System.out.println("number---"+TestMyAppThread.getNumber());
    }
}
