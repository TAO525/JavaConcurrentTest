import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author TAO
 * @Date 2018/2/8 12:04
 */
public class IteratorTest {
    private static List<String> a = new ArrayList<String>(){
        {
            add("1");
            add("2");
            add("4");
            add("3");

        }
    };

    public static void main(String[] args) {
        Iterator<String> iterator = a.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                return null;
            }
        };
    }
}
