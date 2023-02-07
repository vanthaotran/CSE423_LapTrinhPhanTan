import java.util.concurrent.locks.Lock;

public abstract class Attempt2 implements Lock {
    boolean wantCS[] = {false, false};
    public void requestCS (int i) {
        wantCS[i] = true;
        while (wantCS[1-i]); // busy-wait
    }
    public void releaseCS (int i) {
        wantCS[i] = false;
    }
}
