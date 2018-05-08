package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @Author TAO
 * @Date 2018/5/8 10:58
 */
public class ByteArrayInputStreamTest {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(a);
        out.write(b);
        out.write(c);
        byte[] bytes = out.toByteArray();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        int r;
        while ((r = in.read()) != -1){
            System.out.println(r);
        }
    }
}
