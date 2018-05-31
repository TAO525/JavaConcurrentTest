package Temp;

import java.util.Hashtable;

/**
 * @Author TAO
 * @Date 2018/3/2 16:47
 */
public class Test {


    public static void main(String[] args) {
        String a = "sdfa";
        String substring = a.substring(0, 2);
        System.out.println(substring == a);

        Hashtable<String, String> map = new Hashtable<String,String>();
        map.put(null,null);
        map.put("1",null);
        System.out.println(map.get("1"));
        System.out.println(map.get(null));
        Thread at = new ThreadA();
    }
}
