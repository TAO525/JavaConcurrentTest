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

            try {
                while (true) {
                    LOCK.lock();
                    System.out.println("a");
                    B.signal();
                    LOCK.unlock();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {

            }
        }
    }

    private static class B implements Runnable{
        @Override
        public void run() {

            try {
                while (true) {
                    LOCK.lock();
                    B.await();
                    System.out.println("b");
                    C.signal();
                    LOCK.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {

            }
        }
    }

    private static class C implements Runnable{
        @Override
        public void run() {

            try {
                while (true) {
                    LOCK.lock();
                    C.await();
                    System.out.println("c");
                    D.signal();
                    LOCK.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {

            }
        }
    }

    private static class D implements Runnable{
        @Override
        public void run() {

            try {
                while (true) {
                    LOCK.lock();
                    D.await();
                    System.out.println("a");
                    A.signal();
                    LOCK.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {

            }
        }
    }

    public static void main(String[] args) {
        new Thread(new A()).start();
        new Thread(new B()).start();
        new Thread(new C()).start();
        new Thread(new D()).start();

    }
}
