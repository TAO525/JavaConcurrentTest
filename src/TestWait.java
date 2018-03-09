import java.util.concurrent.TimeUnit;

/**
 * @Author TAO
 * @Date 2018/3/6 17:44
 */
public class TestWait<V> extends BaseBoundedBuffer<V> {

    public TestWait(int size) {
        super(size);
    }

    public synchronized V take() throws InterruptedException {
        while (isempty()){
            System.out.println("empty wait");
            wait();
        }
        V v = doget();
        notifyAll();
        return v;
    }

    public synchronized void put(V v) throws InterruptedException {
        while (isfull()) {
            System.out.println("full wait");
            wait();
        }
        doput(v);
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        TestWait<String> stringTestWait = new TestWait<>(1);

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("put 1");
                stringTestWait.put("a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                String take = stringTestWait.take();
                System.out.println("get 1"+take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
