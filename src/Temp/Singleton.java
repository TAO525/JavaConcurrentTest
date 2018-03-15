package Temp;

import sun.security.jca.GetInstance;

/**
 * @Author TAO
 * @Date 2018/3/12 13:56
 */
public class Singleton {

    private static class Helper{
        private static Singleton s = new Singleton();
    }

    private Singleton(){}

    public static Singleton get(){
        return Helper.s;
    }
}
