package Temp;

import java.util.concurrent.Executors;

/**
 * @Author TAO
 * @Date 2018/2/27 14:09
 */
public class TestNcpus {

    public static void main(String[] args) {
        int n = Runtime.getRuntime().availableProcessors();
        System.out.println(n);
    }
}
