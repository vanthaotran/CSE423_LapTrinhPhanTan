import java.util.concurrent.locks.Lock;

public abstract class HWMutex implements Lock {
    TestAndSet lockFlag;
    public void requestCS (int i) {
        while (lockFlag.testAndSet(1) == 1);
    }
    public void releaseCS (int i) {
        lockFlag.testAndSet(0);
    }
}
