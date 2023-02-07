import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] arr =   generateArray();
        T1 t1 = new T1(arr,"T1");
        T1 t2 = new T1(arr,"T2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(t1.getTotal()+t2.getTotal());
    }

    static int[] generateArray(){
        Random rd = new Random();
        int[] arr = new int[200];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt((1000 - 1) + 1) + 1;
        }
        return arr;
    }
}
