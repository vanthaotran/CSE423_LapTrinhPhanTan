public abstract class Attempt3 {
    int turn = 0;
    public void requestCS (int i) {
        while (turn == 1-i);
    }
    public void releaseCS (int i) {
        turn = 1 - i;
    }
}
