package mj;

/**
 * @Author TAO
 * @Date 2018/5/7 18:25
 */
public class TestStackOverFlow {

    static int i = 0;

    public void test(){
        long l = 100000000L;
        long l1 = 100000000L;
        long l2 = 100000000L;
        long l3 = 100000000L;
        long l4 = 100000000L;
        i++;
        test();
    }

    public static void main(String[] args) {
        TestStackOverFlow testStackOverFlow = new TestStackOverFlow();
        try {

            testStackOverFlow.test();
        }catch (Throwable e){
            System.out.println(e);
            System.out.println("height:"+ i);
        }
    }

}
