package Temp;

/**
 * @Author TAO
 * @Date 2018/5/7 10:42
 */
public class TestClass {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> a = Class.forName("Temp.DDD");//会输出hello
//        Class<?> a1 = TestClass.class.getClassLoader().loadClass("Temp.DDD");
    }

}
