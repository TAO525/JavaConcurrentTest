/**
 * @Author TAO
 * @Date 2018/3/6 17:45
 */
public class BaseBoundedBuffer<V> {
    private final V[] buffer;
    private int tail;
    private int head;
    private int count;

    protected BaseBoundedBuffer(int size) {
        this.buffer = (V[])new Object[size];
    }

    protected synchronized final void doput(V v){
        buffer[tail] = v;
        if(++tail == buffer.length)
            tail = 0;
        ++count;
    }

    protected synchronized final V doget(){
        V v = buffer[head];
        if(++head == buffer.length)
            head = 0;
        --count;
        return v;
    }

    public synchronized boolean isempty(){
        return count == 0;
    }

    public synchronized boolean isfull(){
        return count == buffer.length;
    }
}
