package Temp;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author TAO
 * @Date 2018/4/10 9:35
 */
public class ABCDTest extends Thread{

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        int runCount = 4;
        ABCDTest t1 = new ABCDTest(count, "A",0,runCount);
        ABCDTest t2 = new ABCDTest(count, "B",1,runCount);
        ABCDTest t3 = new ABCDTest(count, "C",2,runCount);
        ABCDTest t4 = new ABCDTest(count, "D",3,runCount);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    private AtomicInteger count ;
    private String word=null;
    private int order;
    private int runCount;
    public ABCDTest(AtomicInteger count, String word, int order, int runCount){
        this.count=count;
        this.word=word;
        this.order=order;
        this.runCount=runCount;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (count) {
                if (count.get()%runCount==order) {
                    System.out.println(word+","+count.get());
                    count.getAndAdd(1);
                    count.notifyAll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        count.wait();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
