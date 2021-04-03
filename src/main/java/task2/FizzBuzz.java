package task2;

import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {
    AtomicInteger originNumber = new AtomicInteger();
    AtomicInteger tmpNumber = new AtomicInteger();
    public FizzBuzz(int n) {
        originNumber.set(n);
        tmpNumber.set(1);
    }
    public synchronized void fizz() {
        while (tmpNumber.get() <= originNumber.get()) {
            if(tmpNumber.get() % 3 == 0 && tmpNumber.get() % 5 != 0) {
                System.out.print("fizz" +",");
                tmpNumber.getAndAdd(1);
                notifyAll();
            } else {
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public synchronized void buzz() {
        while (tmpNumber.get() <= originNumber.get()) {
            if(tmpNumber.get() % 3 != 0 && tmpNumber.get() % 5 == 0)  {
                System.out.print("buzz" +",");
                tmpNumber.getAndAdd(1);
                notifyAll();
            } else {
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public synchronized void fizzbuzz() {
        while (tmpNumber.get() <= originNumber.get()) {
            if(tmpNumber.get() % 3 == 0 && tmpNumber.get() % 5 == 0) {
                System.out.print("fizzbuzz" +",");
                tmpNumber.getAndAdd(1);
                notifyAll();
            } else {
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public synchronized void number() {
        while (tmpNumber.get() <= originNumber.get()) {
            if (tmpNumber.get() % 3 == 0 || tmpNumber.get() % 5 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.print(tmpNumber.get() +",");
                tmpNumber.getAndAdd(1);
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        new Thread(fizzBuzz::fizz).start();
        new Thread(fizzBuzz::buzz).start();
        new Thread(fizzBuzz::fizzbuzz).start();
        new Thread(fizzBuzz::number).start();
    }
}
