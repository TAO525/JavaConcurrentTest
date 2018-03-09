import javax.xml.transform.Source;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 渲染页面-> 完成服务
 * @Author TAO
 * @Date 2018/2/7 11:40
 */
public class CompletionServiceTest {

    private final Executor executor;

    public CompletionServiceTest(Executor executor) {
        this.executor = executor;
    }

    private int renderImages(){
        return 10;
    }

    private void renderTxt(){
        try {
            System.out.println("renderTxt");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void renderHtml(){
        CompletionService completionService = new ExecutorCompletionService(executor);
        int images = renderImages();
        for (int i = 0; i < images; i++) {
            Callable<Integer> callable = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    int time = new Random().nextInt(8);
                    TimeUnit.SECONDS.sleep(time);
                    return time;
                }
            };
            completionService.submit(callable);
        }
        renderTxt();
        for(int i = 0; i < images; i++){
            Future poll = null;
            try {
                poll = completionService.take();
                System.out.println(poll.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletionServiceTest completionServiceTest = new CompletionServiceTest(executorService);
        completionServiceTest.renderHtml();
        executorService.shutdown();
    }
}
