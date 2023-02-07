import java.util.Random;

class BoundedBuffer {

    // variables
    public final int minValue = 0; // min boundary var
    public final int maxValue = 100; // max boundary var
    public int count = 20; // counting var
    public int value = 0;
    Random r = new Random();
//    value = r.nextInt()
    // variables


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