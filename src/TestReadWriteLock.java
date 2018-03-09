import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author TAO
 * @Date 2018/3/6 17:20
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        lock.readLock().lock();
        System.out.println("get read lock");
        try{
            new Thread(()->{
                System.out.println(3);
                lock.readLock().lock();
                try {
                    System.out.println("get read lock");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("unlock read");
                    lock.readLock().unlock();
                }
            }).start();

            new Thread(()->{
                System.out.println(1);
                lock.writeLock().lock();
                try {
                    System.out.println("get write lock");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("unlock write");
                    lock.writeLock().unlock();
                }
            }).start();

            new Thread(()->{
                System.out.println(2);
                lock.readLock().lock();
                try {
                    System.out.println("get read lock");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("unlock read");
                    lock.readLock().unlock();
                }
            }).start();
        }finally {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("unlock read");
            lock.readLock().unlock();
        }
    }
}
