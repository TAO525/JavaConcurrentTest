import java.awt.*;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 测试死锁
 * @Author TAO
 * @Date 2018/2/28 15:37
 */
public class TestDeadLock {
    private static final Object tieLock = new Object();

    public static void transferMoney(final Account to,final Account from,final int amount) throws Exception {
        //内部类简化代码
        class Helper{
            public void transfer() throws Exception {
                if(amount < 0){
                    throw new Exception();
                }
                else {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("扣钱"+from);
                    System.out.println("加钱"+to);
                }
            }
        }
        int hashto = System.identityHashCode(to);
        int hashfrom = System.identityHashCode(from);
        if(hashto<hashfrom){
            synchronized (to){
                synchronized (from){
                    new Helper().transfer();
                }
            }
        }else if(hashto > hashfrom){
            synchronized (from){
                synchronized (to){
                    new Helper().transfer();
                }
            }
        }else{
            synchronized (tieLock){
                synchronized (from){
                    synchronized (to){
                        new Helper().transfer();
                    }
                }
            }
        }
    }

    private static final int threads = 200;
    private static final int accounts = 5;
    private static final int iterations = 100000;

    public static void main(String[] args) {
        Random random = new Random();
        Account[] accs = new Account[accounts];
        for(int i=0;i<accounts;i++){
            accs[i] = new Account(random.nextInt(10));
        }
        class TransferThread extends Thread{
            @Override
            public void run() {
                for(int i=0;i<iterations;i++){
                    int to = random.nextInt(accounts);
                    int from = random.nextInt(accounts);
                    int amount = random.nextInt(1000);
                    try {
                        transferMoney(accs[to],accs[from],amount);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (int i = 0; i < threads; i++) {
            new TransferThread().start();
        }
    }
}

class Account{
    private int weight;
    public Account(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Account{" +
                "weight='" + weight + '\'' +
                '}';
    }
}
