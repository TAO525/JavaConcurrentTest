import java.util.concurrent.TimeUnit;

/**
 * @Author TAO
 * @Date 2018/2/26 16:45
 */
public class TestSystemOut {

    private static class LongThread implements Runnable{
        @Override
        public void run() {
//            while (true){
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1");
//            }
        }
    }

    public static void main(String[] args) {
        LongThread longThread = new LongThread();
        longThread.run();
        System.exit(1);
        System.out.println(2);
    }
}
