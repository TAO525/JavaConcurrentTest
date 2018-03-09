import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author TAO
 * @Date 2018/1/23 18:03
 */
public class ForTest {

    public static void main(String[] args) {
        final List<Integer> list = new CopyOnWriteArrayList<Integer>(Arrays.asList(1,2,3,5));

        for(Integer i : list){
            if(i == 1){
                list.remove(i);
            }
            System.out.println(i);
        }
        System.out.println(list);

        Map<List,Object> map = new HashMap<List, Object>();
        map.put(list,3);
        System.out.println(map);
    }
}
