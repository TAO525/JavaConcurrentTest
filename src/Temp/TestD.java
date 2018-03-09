package Temp;

import java.text.DecimalFormat;

/**
 * @Author TAO
 * @Date 2018/2/26 15:20
 */
public class TestD {

    private static int a = 2;

    private static class A{
        public void test(){
            System.out.println(a);
        }
    }

    public static void main( String[] args )
    {
       /* double d=4.015*100;
        System.out.println(d);
        DecimalFormat dec = new DecimalFormat("#.00");
        System.out.println(dec.format(d));*/
        new A().test();
    }
}
