package Temp;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author TAO
 * @Date 2018/5/17 16:16
 */
public class StringPool2 {

    //Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
