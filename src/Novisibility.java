/**
 * 不可见性（volatile）
 * @Author TAO
 * @Date 2018/1/23 18:03
 */
public class Novisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while (!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
