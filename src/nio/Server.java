package nio;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author TAO
 * @Date 2018/5/8 23:36
 */
public class Server {

    public static void main(String[] args) throws IOException {
        //准备工作
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8000);
        server.bind(inetSocketAddress);
        server.configureBlocking(false);//!
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);

        //尝试多个端口监听
        ServerSocketChannel server2 = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress(8001);
        server2.bind(inetSocketAddress2);
//        server2.socket().bind(inetSocketAddress,5);//可以设置tcp accept池
        server2.configureBlocking(false);//!
        server2.register(selector,SelectionKey.OP_ACCEPT);

        // 轮询式地获取选择器上已经准备就绪的事件
        //selector.select()方法是阻塞的
        while (selector.select()>0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if(key.isAcceptable()){
//                    SocketChannel client = server.accept();
                    ServerSocketChannel serverY = (ServerSocketChannel)key.channel();
                    SocketChannel client = serverY.accept();
                    client.configureBlocking(false);
                    client.register(selector,SelectionKey.OP_READ);
                }
                else if(key.isReadable()){
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (client.read(buffer) >0){
                        buffer.flip();
                        byte[] array = buffer.array();
                        System.out.println(new String(array));
                        buffer.clear();
                    }

                }
                iterator.remove();
            }

        }

    }
}
