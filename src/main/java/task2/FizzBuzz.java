package task2;

public class FizzBuzz {
    private volatile int n;
    private volatile int tmp = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }
    public synchronized void fizz() {
        while (tmp <= n) {
            if(tmp % 3 == 0 && tmp % 5 != 0) {
                System.out.print("fizz" +",");
                tmp++;
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
        while (tmp <= n) {
            if(tmp % 3 != 0 && tmp % 5 == 0)  {
                System.out.print("buzz" +",");
                tmp++;
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
        while (tmp <= n) {
            if(tmp % 3 == 0 && tmp % 5 == 0) {
                System.out.print("fizzbuzz" +",");
                tmp++;
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
        while (tmp <= n) {
            if (tmp % 3 == 0 || tmp % 5 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.print(tmp +",");
                tmp ++;
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
