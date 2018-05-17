package Temp;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author TAO
 * @Date 2018/5/17 16:16
 */
public class StringPool {

    //1.字符串常量由永久代转移到堆中。
    static String base = "String";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}
