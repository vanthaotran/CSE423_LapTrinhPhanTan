import java.util.Random;
import java.util.Random;

public class T1 extends Thread {
    int[] arr;
    String threadName;
    public T1(int[] consArr,String threadName) {
        arr = consArr;
        this.threadName = threadName;
    }
    private int total = 0;

    @Override
    public void run() {
        super.run();
        if(threadName.equals("T1")){
            for(int i =0; i<arr.length/2;i++){
                if(isPerfectSquare(arr[i])){
                    System.out.println("T1: " +arr[i]);
                    total++;
                }
            }
        }
        else{
            for(int i = arr.length / 2; i < arr.length; i++){
                if(isPerfectSquare(arr[i])){
                    System.out.println("T2: " +arr[i]);
                    total++;
                }
            }
        }

    }

    public int getTotal(){
        return total;
    }

    static boolean isPerfectSquare(int x)
    {
        if (x >= 0) {
            int sr = (int)Math.sqrt(x);
            return ((sr * sr) == x);
        }
        return false;
    }





}

