import java.util.concurrent.ExecutionException;

/**
 * @Author TAO
 * @Date 2018/2/5 15:31
 */
public interface Computable<k,v> {
    v compu(k param) throws ExecutionException, InterruptedException;
}
