import java.net.SocketOption;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class MyThread extends Thread{
    int myId;
    Lock lock;
    Random r = new Random();

    public MyThread (int id, Lock lock) {
        myId = id;
        lock = lock;
    }

    void nonCriticalSection () {
        System.out.println(myId + " is not in CS");
        Util.mySleep(r.nextInt(1000));
    }

    void CriticalSection () {
        System.out.println(myId + " is in CS ***");

        Util.mySleep((r.nextInt(1000)));
    }

    public void run() {
        while (true) {
            lock.requestCS(myId);
            CriticalSection();
            lock.releaseCS(myId);
            nonCriticalSection();
        }
    }

    public static void main (String [] args) throws Exception {
        MyThread t[];
        int N = Integer.parseInt(args[0]);
        t = new MyThread[N];
        Lock lock = new Bakery(N);
        for (int i = 0; i < N; i++) {
            t[i] = new MyThread(i, lock);
            t[i].start();
        }
    }
}
