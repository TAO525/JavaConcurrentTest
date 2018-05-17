package Temp;

/**
 * @Author TAO
 * @Date 2018/5/16 10:45
 */
public class E {

    private int a;

    private static int b;

    public void hello(){
        int b = a+10;
        System.out.println(b);
    }

    public static void main(String[] args) {
        E e = new E();
        e.hello();
        System.out.println(E.b);
    }
}
