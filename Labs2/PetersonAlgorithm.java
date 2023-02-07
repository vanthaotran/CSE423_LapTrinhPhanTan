import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class PetersonAlgorithm implements Lock {

    boolean wantCS[] = {false, false};
    int turn = 1;
    public void requestCS (int i) {
        int j = 1 - i;
        wantCS[i] = true;
        turn = j;
        while (wantCS[j] &&& (turn == j));
    }

    public void releaseCS (int i) {
        wantCS[i] = false;
    }



    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
