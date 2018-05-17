package Temp;

/**
 * @Author TAO
 * @Date 2018/5/17 15:00
 */
public class E2 {

    //父类
    static class SuperClass {
        //静态变量value
        public static int value = 666;
        //静态块，父类初始化时会调用
        static{
            System.out.println("父类初始化！");
        }
    }

    //子类
    static class SubClass extends SuperClass{
        //静态块，子类初始化时会调用
        static{
            System.out.println("子类初始化！");
        }
    }

    //主类、测试类
        public static void main(String[] args){
            System.out.println(SubClass.value);
        }
}
