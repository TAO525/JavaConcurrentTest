import java.security.Principal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author TAO
 * @Date 2018/3/7 14:58
 */
public class ConditionBoundedBuffer<V>{
    private final Lock lock = new ReentrantLock();

    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    private int tail,head,count;

    private final V[] buffer;

    public ConditionBoundedBuffer(int size) {
        buffer = (V[])new Object[size];
    }

    public void put(V v) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length){
                System.out.println("not full await");
                notFull.await();
            }
            buffer[tail] = v;
            ++count;
            if(tail++ == buffer.length){
                tail = 0;
            }
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public V take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0){
                System.out.println("not empty await");
                notEmpty.await();
            }
            V v = buffer[head];
            buffer[head] = null;//
            --count;
            if(head++ == buffer.length){
                head = 0;
            }
            notFull.signal();
            System.out.println("1");
            return v;
        }finally {
            System.out.println("2");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ConditionBoundedBuffer<String> buffer = new ConditionBoundedBuffer<String>(1);
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(4);
                buffer.put("wwe");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        String take = buffer.take();
        System.out.println(take);
    }
}
