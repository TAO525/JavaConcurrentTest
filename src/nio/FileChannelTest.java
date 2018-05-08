package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author TAO
 * @Date 2018/5/8 15:23
 */
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("C:\\Users\\gutao\\1.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = channel.read(buf);
        while (bytesRead != -1){
            System.out.println("read"+bytesRead);
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.clear();//清空继续读
            bytesRead = channel.read(buf);
        }
        file.close();
    }
}
