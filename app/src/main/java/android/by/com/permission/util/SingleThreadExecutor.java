package android.by.com.permission.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by frankyang on 7/27/15.
 */

/**
 * 创建只有一个线程的线程池
 */
public class SingleThreadExecutor {
    private static ExecutorService inst = null;
    private SingleThreadExecutor() {};
    public static ExecutorService getInst() {
        if (inst == null ) {
            synchronized (SingleThreadExecutor.class) {
                if (inst == null) {
                    inst = Executors.newSingleThreadExecutor();
                }
            }
        }
        return  inst;
    }
}
