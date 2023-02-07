import java.util.Random;

public class Labs3_Van {
    public class BinarySemaphore {
        boolean value;
        BinarySemaphore(boolean initValue) {
            value = initValue;
        }
        public synchronized void P() {
            while (value == false)
                Labs3_Khai.Util.myWait(this);// in queue of blocked processes
            value = false;
        }
        public synchronized void V() {
            value = true;
            notify();
        }
    }

    public class BoundedBuffer {
        final int size = 10;
        int[] buffer = new int[size];
        int inBuf = 0, outBuf = 0;
        Labs3_Khai.BinarySemaphore mutex = new Labs3_Khai.BinarySemaphore(true);
        Labs3_Khai.CountingSemaphore isEmpty = new Labs3_Khai.CountingSemaphore(0);
        Labs3_Khai.CountingSemaphore isFull = new Labs3_Khai.CountingSemaphore(size);

        public void deposit(int value) {
            isFull.P();// wait if buffer is full
            mutex.P(); // ensures mutual exclusion
            buffer[inBuf] = value; // update the buffer
            inBuf = (inBuf + 1) % size;
            mutex.V();
            isEmpty.V();  // notify any waiting consumer
        }
        public int fetch() {
            int value;
            isEmpty.P(); // wait if buffer is empty
            mutex.P();  // ensures mutual exclusion
            value = buffer[outBuf]; //read from buffer
            outBuf = (outBuf + 1) % size;
            mutex.V();
            isFull.V(); // notify any waiting producer
            return value;
        }
    }

    public class Consumer implements Runnable {
        Labs3_Khai.BoundedBuffer b = null;
        int timeSleep = 0;

        public Consumer(Labs3_Khai.BoundedBuffer initb, int timeSleep) {
            b = initb;
            this.timeSleep = timeSleep;
            new Thread(this).start();
        }

        static boolean isEvenNumber(int x)
        {
            return x % 2 == 0;
        }
        @Override
        public void run() {
            int item;
            while (true) {
                item = b.fetch();
                if (isEvenNumber(item)) {

                    System.out.println("T2: " + item +" Chan");
                }
                else{
                    System.out.println("T2: " + item +" Le");

                }
                Labs3_Khai.Util.mySleep(timeSleep);
            }
        }
    }

    public class CountingSemaphore {
        int value;
        public CountingSemaphore(int initValue) {
            value = initValue;
        }
        public synchronized void P() {
            value--;
            if (value < 0) Labs3_Khai.Util.myWait(this);
        }
        public synchronized void V() {
            value++;
            if (value <= 0) notify();
        }
    }

    public class Producer implements Runnable {
        Labs3_Khai.BoundedBuffer b = null;
        int timeSleep = 0;

        public Producer(Labs3_Khai.BoundedBuffer initb, int timeSleep) {
            b = initb;
            this.timeSleep = timeSleep;
            new Thread(this).start();
        }

        @Override
        public void run() {
            int item;
            Random r = new Random();
            while (true) {
                item = r.nextInt(200);
                System.out.println("T1:" + item);
                b.deposit(item);
                Labs3_Khai.Util.mySleep(timeSleep);
            }
        }
    }

    public class Util {
        public static void mySleep(int time) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
            }
        }
        public static void myWait(Object obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
            }
        }
    }



    public  void main(String[] args) {
        Labs3_Khai.BoundedBuffer buffer = new BoundedBuffer();
        Labs3_Khai.Producer producer = new Producer(buffer,50);
        Labs3_Khai.Consumer consumer = new Consumer(buffer,1000);
    }
}
