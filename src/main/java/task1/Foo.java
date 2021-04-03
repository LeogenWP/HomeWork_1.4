package task1;

import java.util.concurrent.atomic.AtomicBoolean;

public class Foo {
    AtomicBoolean f1 = new AtomicBoolean(true);
    AtomicBoolean f2 = new AtomicBoolean(false);
    AtomicBoolean f3 = new AtomicBoolean(false);

    public  void first() {
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
    }
    public  void second() {
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

    }
    public  void third() {
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
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        new Thread(foo::first).start();
        new Thread(foo::second).start();
        new Thread(foo::third).start();
    }
}




