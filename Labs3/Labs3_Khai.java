import java.util.Random;

public class Labs3_Khai {
    public class BinarySemaphore {
        boolean value;
        BinarySemaphore(boolean initValue) {
            value = initValue;
        }
        public synchronized void P() {
            while (value == false)
                Util.myWait(this);// in queue of blocked processes
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
        BinarySemaphore mutex = new BinarySemaphore(true);
        CountingSemaphore isEmpty = new CountingSemaphore(0);
        CountingSemaphore isFull = new CountingSemaphore(size);

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
        BoundedBuffer b = null;
        int timeSleep = 0;

        public Consumer(BoundedBuffer initb, int timeSleep) {
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
                Util.mySleep(timeSleep);
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
            if (value < 0) Util.myWait(this);
        }
        public synchronized void V() {
            value++;
            if (value <= 0) notify();
        }
    }

    public class Producer implements Runnable {
        BoundedBuffer b = null;
        int timeSleep = 0;

        public Producer(BoundedBuffer initb, int timeSleep) {
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
                Util.mySleep(timeSleep);
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



    public void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer();
        Producer producer = new Producer(buffer,50);
        Consumer consumer = new Consumer(buffer,1000);
    }
}
