import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author TAO
 * @Date 2018/3/15 18:04
 */
public class ThreadB {
    public static class CallThread implements Callable{
        @Override
        public Object call() throws Exception {
            TimeUnit.SECONDS.sleep(8);
            return 10;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Object> objectFutureTask = new FutureTask<Object>(new CallThread());
        new Thread(objectFutureTask).start();
        System.out.println(objectFutureTask.get());
    }
}
