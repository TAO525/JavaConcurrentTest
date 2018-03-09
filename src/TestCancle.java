import org.omg.CORBA.TIMEOUT;

import javax.swing.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author TAO
 * @Date 2018/2/8 15:29
 */
public class TestCancle implements Runnable{
    private final List<BigInteger> list = new ArrayList<>();

    private volatile boolean flag = false;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!flag){
            p = p.nextProbablePrime();
            synchronized (this){
                list.add(p);
            }
        }
    }

    public void cancle(){
        this.flag = true;
    }

    public List<BigInteger> get(){
        return new ArrayList<>(list);
    }

    public static void main(String[] args) {
        TestCancle testCancle = new TestCancle();
        new Thread(testCancle).start();
//        new Thread(testCancle).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            testCancle.cancle();
        }
        System.out.println(testCancle.get());
    }
}
