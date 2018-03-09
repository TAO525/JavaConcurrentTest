import com.sun.org.apache.bcel.internal.generic.LLOAD;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 待返回结果的任务
 * @Author TAO
 * @Date 2018/2/5 15:28
 */
public class FutureTaskTest<k,v> implements Computable<k,v>{
    private final Map<k,Future<v>> cache = new ConcurrentHashMap<k, Future<v>>();

    private final Computable<k,v> computable;

    public FutureTaskTest(Computable<k,v> computable) {
        this.computable = computable;
    }

    @Override
    public v compu(final k param){
        Future<v> vFuture = cache.get(param);
        if(vFuture == null){
            Callable<v> callable = new Callable<v>() {
                @Override
                public v call() throws Exception {
                    return computable.compu(param);
                }
            };
            FutureTask<v> vFutureTask = new FutureTask<v>(callable);
            vFuture = cache.putIfAbsent(param, vFutureTask);
            if(vFuture == null){
                vFuture = vFutureTask;
                System.out.println("yyy");
                vFutureTask.run();
            }
        }else {
            vFuture = cache.get(param);
        }
        try {
            return vFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final FutureTaskTest<String,String> test = new FutureTaskTest<String, String>(new Computable<String, String>() {
            @Override
            public String compu(String param) throws ExecutionException, InterruptedException {
                System.out.println(Thread.currentThread());
                TimeUnit.SECONDS.sleep(5);
                return param;
            }
        });
      /*  long l = System.nanoTime();
        for (int i = 0; i < 1; i++) {
            System.out.println(test.compu(i+""));
        }
        System.out.println(System.nanoTime()-l);
        long kk = System.nanoTime();
        for (int i = 0; i < 1; i++) {
            System.out.println(test.compu(i+""));
        }
        System.out.println(System.nanoTime() - kk);*/

        long l = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            new Thread(()->{System.out.println(test.compu(finalI +""));}).start();
        }
        System.out.println(System.nanoTime()-l);
        long kk = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            System.out.println(test.compu(i+""));
        }
        System.out.println(System.nanoTime() - kk);
    }
}
