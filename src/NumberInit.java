/**
 * @Author TAO
 * @Date 2018/3/21 12:48
 */
public class NumberInit {
    private String[] chars = {"A", "B", "C", "D"};
    private final int N = 4;

    public void run() {
        for (int i = 0; i < N; i++) {
            createThread(i);
        }
    }

    private void createThread(final int i) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(chars[i]);
            }
        }, "thread" + i);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NumberInit().run();
    }
}