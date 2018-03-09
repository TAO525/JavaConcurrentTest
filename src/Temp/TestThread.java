package Temp;

/**
 * @Author TAO
 * @Date 2018/2/27 16:21
 */
public class TestThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        });
        thread.start();
        thread.start();//start 不能重复启动
    }
}
