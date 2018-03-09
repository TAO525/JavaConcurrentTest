import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args){
        System.out.println(Thread.currentThread().getId());
//        A a = new A();
//        a.start();
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("catch");
            System.out.println(Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().isInterrupted());
        }
//        a.test();
    }
}

class A extends Thread{
    public void test(){
        interrupt();
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getId()+"run");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
