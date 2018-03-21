package Temp;

/**
 * @Author TAO
 * @Date 2018/3/11 16:39
 */
public class ThreadA extends Thread{
    @Override
    public void run() {
        try {
            synchronized (this) {
                System.out.println("hello");
                this.wait();//必须要拥有线程的锁
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.start();
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.interrupt();
    }
}
