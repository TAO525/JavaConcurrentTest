package Temp;

/**
 * @Author TAO
 * @Date 2018/4/26 16:09
 */
public class AAAA extends AAA{
    public static String aaa(){
        return AAA.aaa();
    }

    public static void main(String[] args) {
        System.out.println(aaa());
    }
}
