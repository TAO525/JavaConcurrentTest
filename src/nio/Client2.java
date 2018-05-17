package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @Author TAO
 * @Date 2018/5/8 23:35
 */
public class Client2 {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8001));
        Scanner scanner = new Scanner(System.in);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (scanner.hasNext()){
            String next = scanner.next();
            byte[] bytes = next.getBytes();
            buffer.put(bytes);

            buffer.flip();//!
            socketChannel.write(buffer);
            buffer.clear();
        }
    }
}
