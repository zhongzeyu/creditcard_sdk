package pay.androidcredit.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PayThreadPoolManager {
    private ExecutorService cachePool = Executors.newCachedThreadPool();

    private ExecutorService singlePool = Executors.newSingleThreadExecutor();

    private static final class SingletonHolder {
        private static final pay.androidcredit.util.PayThreadPoolManager instance = new pay.androidcredit.util.PayThreadPoolManager();
    }

    private PayThreadPoolManager() {
    }

    public static void executeInSinglePool(Runnable r) {
        if (r != null) {
            SingletonHolder.instance.singlePool.execute(r);
        }
    }

    public static void executeInCachePool(Runnable r) {
        if (r != null) {
            SingletonHolder.instance.cachePool.execute(r);
        }
    }
}
