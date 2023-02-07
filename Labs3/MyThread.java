

public class MyThread extends Thread {
    Lock lock;
    int tid;

    public MyThread(int tid, Lock lock) {
        this.tid = tid;
        this.lock = lock;
    }

    void CS() {
        System.out.println("\n" + tid + "  CS");
        System.out.println("\n" + tid + "  CS");
    }

    void nonCS() {
        System.out.println("\n" + tid + " khong phai CS");
        System.out.println("\n" + tid + " khong phai CS");
    }

    @Override
    public void run() {
        while (true) {
            lock.requestCS(tid);
            CS();
            lock.releaseCS(tid);
            nonCS();
        }
    }

}
