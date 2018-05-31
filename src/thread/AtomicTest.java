package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author TAO
 * @Date 2018/5/22 14:16
 */
public class AtomicTest {
    static AtomicInteger integer = new AtomicInteger(10);

    public static void main(String[] args) {
        System.out.println(integer.compareAndSet(11,20));
    }
}
