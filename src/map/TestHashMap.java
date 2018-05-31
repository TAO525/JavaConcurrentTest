package map;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @Author TAO
 * @Date 2018/5/30 16:05
 */
public class TestHashMap {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("a","aaa");

        Class<? extends HashMap> aClass = map.getClass();
        Method capacity = aClass.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println(capacity.invoke(map));

        Field size = aClass.getDeclaredField("size");
        size.setAccessible(true);
        System.out.println(size.get(map));
    }
}
