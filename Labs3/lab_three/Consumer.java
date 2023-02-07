import java.util.Random;

public class Consumer implements Runnable  {
    BoundedBuffer b = null;
    int timeSleep = 10000;

    public Consumer(BoundedBuffer initb, int timeSleep) {
        b = initb;
        this.timeSleep = timeSleep;
        new Thread(this).start();
    }

    public  void run() { // T2 decrement
        int item;
        Random r = new Random();
        while(true) {
            item = b.fetch();
            while (b.count <= b.minValue) {
                System.out.println("being in min staus, waiting");
                synchronized(this) {
                    try {
                        wait();
                    } catch (InterruptedException e) { }
                }
                Util.mySleep(timeSleep);
            }
            if (b.count-item >= b.minValue) {
                b.count -= item;
                System.out.println("decrement :" + b.count);
            }
        }
    }
}
