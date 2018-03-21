import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author TAO
 * @Date 2018/3/7 14:58
 */
public class TestCondition {

    private static final Lock LOCK = new ReentrantLock();
    private static final Condition A = LOCK.newCondition();
    private static final Condition B = LOCK.newCondition();
    private static final Condition C = LOCK.newCondition();
    private static final Condition D = LOCK.newCondition();

    private static class A implements Runnable{
        @Override
        public void run() {
            LOCK.lock();
            try {
                while (true) {
                    A.await();
                    System.out.println("a");
                    B.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                LOCK.unlock();
            }
        }
    }

    private static class B implements Runnable{
        @Override
        public void run() {
            LOCK.lock();
            try {
                while (true) {
                    B.await();
                    System.out.println("b");
                    C.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                LOCK.unlock();
            }
        }
    }

    private static class C implements Runnable{
        @Override
        public void run() {
            LOCK.lock();
            try {
                while (true) {
                    C.await();
                    System.out.println("c");
                    D.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                LOCK.unlock();
            }
        }
    }

    private static class D implements Runnable{
        @Override
        public void run() {
            LOCK.lock();
            try {
                while (true) {
                    D.await();
                    System.out.println("a");
                    A.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                LOCK.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new A()).start();
        new Thread(new B()).start();
        new Thread(new C()).start();
        new Thread(new D()).start();
        LOCK.lock();
        A.signal();
        LOCK.unlock();
    }
}
