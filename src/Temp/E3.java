package Temp;

/**
 * @Author TAO
 * @Date 2018/5/17 15:00
 */
public class E3 {
    public static void main(String[] args) {
        Person p = new Person(1,"aa");
        change(p);
        System.out.println(p);
    }

    private static void change(Person p){
        p = new Person(2,"bb");
    }
}
