package task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo {
    AtomicInteger atomInt = new AtomicInteger(1);
    public synchronized void first() {
        while(atomInt.get() != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("first");
        atomInt.getAndSet(2);
        notifyAll();
    }
    public synchronized void second() {
        while(atomInt.get() != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("second");
        atomInt.getAndSet(3);
        notifyAll();
    }
    public synchronized void third() {
        while(atomInt.get() != 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("third");
        atomInt.getAndSet(1);
        notifyAll();
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        new Thread(foo::third).start();
        new Thread(foo::second).start();
        new Thread(foo::first).start();

    }
}




