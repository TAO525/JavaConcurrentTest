import java.util.Vector;

/**
 * @Author TAO
 * @Date 2018/3/16 15:57
 */
public class TestVector {
    public static void main(String[] args) {
        Vector<String> strings = new Vector<>(10);
//        List<String> strings = new ArrayList<>(10);
        strings.add("1");
        strings.add("2");
        strings.add("3");
        for(String a : strings){
            System.out.println(strings.remove(a));
        }
    }
}
