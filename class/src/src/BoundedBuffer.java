public class BoundedBuffer {
    final int size = 10;
    double[] buffer = new double[size];
    int inBuf = 0, outBuf = 0;
    BinarySemaphore mutex = new BinarySemaphore(true);
    CountingSemaphore isEmpty = new CountingSemaphore(size);

    public void deposit() {
        isFull.P();
        mutex.P();
        buffer[inBuf] = value;
        
    }
    public void fetch() {

    }
}
