package Temp;

/**
 * @Author TAO
 * @Date 2018/4/10 9:56
 */
public enum Singlet {
    INSTANCE;

    public void test(){
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        Singlet.INSTANCE.test();
    }
}
