import java.util.concurrent.locks.Lock;

//interface Lock {
//    void requestCS (int tid) ;
//    void releaseCS (int tid);
//}

public abstract class Dekker implements Lock {
    boolean wantCS[] = {false, false};
    int turn = 1;

    public void requestCS (int i) {
        int j = 1 - i;
        wantCS[i] = true;
        while (wantCS[j]) {
            if(turn == j) {
                wantCS[i] = false;
                while (turn == j); // busy-wait
                wantCS[i] = true;
            }
        }
    }

    public void releaseCS (int i) {
        turn = 1 - i;
        wantCS[i] = false;
    }
}
