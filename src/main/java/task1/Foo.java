package task1;

public class Foo {
    private boolean f1 = true;
    private boolean f2 = false;
    private boolean f3 = false;

    public synchronized void first() {
        while(!f1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("first");
        f1 = false;
        f2 = true;
        notifyAll();
    }
    public synchronized void second() {
        while(!f2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("second");

        f2 = false;
        f3 = true;
        notifyAll();

    }
    public synchronized void third() {
        while(!f3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print("third");
        f3 = false;
        f1 = true;
        notifyAll();
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        new Thread(foo::first).start();
        new Thread(foo::second).start();
        new Thread(foo::third).start();
    }
}




