package Temp;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author TAO
 * @Date 2018/3/9 15:46
 */
public interface B extends A,C{
    default void testA(){
        System.out.println("aaa");
    }

    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(0);

    }
}
