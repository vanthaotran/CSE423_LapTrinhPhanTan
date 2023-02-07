import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public abstract class Attempt1 implements Lock {
    boolean openDoor = true;
    public void requestCS (int i) {
        while (!openDoor) ; // busy-wait // when openDooe = false
        openDoor = false;
    }
    public void releaseCS (int i) {
        openDoor = true;
    }
}
