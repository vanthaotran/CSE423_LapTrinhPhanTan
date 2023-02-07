import java.util.Random;

public class Main {
    public class BoundedCounter {
    }
    public synchronized void increment() {
    }
    public synchronized void decrement() {
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer buffer = new BoundedBuffer();

        Producer producer = new Producer(buffer, 1000); // T1 increment

        Consumer consumer = new Consumer(buffer, 1000); // T2 decrement
    }
}