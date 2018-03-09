import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author TAO
 * @Date 2018/2/27 17:02
 */
public class TestThreadPoolExecutors {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        if(executorService instanceof ThreadPoolExecutor){
            ((ThreadPoolExecutor) executorService).setCorePoolSize(10);
        }
        else {
            throw  new AssertionError("不该发生的异常");
        }
    }

}
