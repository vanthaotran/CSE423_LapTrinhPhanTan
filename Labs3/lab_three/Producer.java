import java.util.Random;
public class Producer implements Runnable {
    BoundedBuffer b = null;
    int timeSleep = 10000;

    public Producer(BoundedBuffer initb, int timeSleep) {
        b = initb;
        this.timeSleep = timeSleep;
        new Thread(this).start();
    }

    public  void run() { // T1 increment
        int item;
        Random r = new Random();
        while(true) {
            item = r.nextInt(10);
            while (b.count + item > b.maxValue) { // sai
                System.out.println("being in max staus, waiting");
                synchronized(this) {
                    try {
                        wait();
                    } catch (InterruptedException e) { }
                }
//                Util.mySleep(timeSleep);
            }
            if (b.count + item <= b.maxValue) {
                b.count += item;
                System.out.println("increment :" + b.count);
            }
            b.deposit(item);
        }

    }
}
