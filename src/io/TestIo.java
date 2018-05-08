package io;

import sun.nio.ch.SelectorProviderImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.AbstractSelector;

/**
 * @Author TAO
 * @Date 2018/5/8 10:25
 */
public class TestIo {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = null;
        Selector selector = Selector.open();
        SelectableChannel channel = new AbstractSelectableChannel(new SelectorProviderImpl() {
            @Override
            public AbstractSelector openSelector() throws IOException {
                return null;
            }
        }){
            @Override
            public int validOps() {
                return 0;
            }

            @Override
            protected void implCloseSelectableChannel() throws IOException {

            }

            @Override
            protected void implConfigureBlocking(boolean block) throws IOException {

            }
        };

        SelectionKey register = channel.register(selector, SelectionKey.OP_READ);
       /* int select = selector.select();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.signal();*/
    }
}
