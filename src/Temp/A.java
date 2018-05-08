package Temp;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author TAO
 * @Date 2018/3/9 15:46
 */
public interface A {

    default void a(){
        System.out.println("a");
    }

    public static void main(String[] args) {
        Collection a = new HashSet();
        Map map = new HashMap();
    }
}
