/**
 * @Author TAO
 * @Date 2018/2/26 16:29
 */
public class TestUncatchException {

    private static class WrongThread implements Runnable{
        @Override
        public void run() {
            System.out.println(1/0);
        }
    }

    private static class WrongThread2 extends Thread{
        @Override
        public void run() {
            System.out.println(1/0);
        }
    }

    private static class MyUncatchException implements  Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("hello world");
        }
    }

    public static void main(String[] args) {
//        WrongThread wrongThread = new WrongThread();
//        Thread.setDefaultUncaughtExceptionHandler(new MyUncatchException());
//        wrongThread.run();
        WrongThread2 wrongThread2 = new WrongThread2();
        wrongThread2.setUncaughtExceptionHandler(new MyUncatchException());
        wrongThread2.start();
    }

}
