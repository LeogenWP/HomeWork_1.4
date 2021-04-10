package task1;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Boo {

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    public Future<String> first() {
        return executor.submit(()->"first");
    }
    public Future<String>  second() {
        return executor.submit(()->"second");
    }
    public Future<String>  third() {
        return executor.submit(()->"third");
    }

    public static void main(String[] args) {
        Boo boo = new Boo();
        Future<String> future1 = boo.first();
        Future<String> future2 = boo.second();
        Future<String> future3 = boo.third();

        try {
            System.out.println(future1.get() + future2.get() + future3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        boo.executor.shutdown();
    }
}
