package task1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Foo {
    AtomicBoolean f1 = new AtomicBoolean(true);
    AtomicBoolean f2 = new AtomicBoolean(false);
    AtomicBoolean f3 = new AtomicBoolean(false);

    public synchronized String first() {
        while(!f1.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("first");
        f1.getAndSet(false);
        f2.getAndSet(true);
        notifyAll();
        return "first";
    }
    public synchronized String second() {
        while(!f2.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("second");
        f2.getAndSet(false);
        f3.getAndSet(true);
        notifyAll();
        return "second";

    }
    public synchronized String third() {
        while(!f3.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print("third");
        f3.getAndSet(false);
        f1.getAndSet(true);
        notifyAll();
        return "third";
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(foo::second);
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(foo::first);
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(foo::third);

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1,future2,future3);

        try {
            combinedFuture.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch (Exception e ) {
            System.out.println("Something has been crashed");
        }

        //assertTrue(future1.isDone());
        //assertTrue(future2.isDone());
        //assertTrue(future3.isDone());


    }
}




