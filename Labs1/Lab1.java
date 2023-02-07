import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lab1 extends Thread{
    public static int n;
    public final Runnable run1;
    public final Runnable run2;
    private final ArrayList<Integer> A;
    public static final ArrayList<Integer> t1 = new ArrayList<>();
    public static final ArrayList<Integer> t2 = new ArrayList<>();

    public Lab1(ArrayList<Integer> A) {
        this.A = A;
        run1 = new Runnable() {
            @Override
            public void run() {
                t1Process();
            }
        };
        run2 = new Runnable() {
            @Override
            public void run() {
                t2Process();
            }
        };
    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        n = sc.nextInt();
        sc.close();
        ArrayList<Integer> A = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            A.add(random.nextInt(100));
        }

        System.out.println("A: " + A); // A array random
        Lab1 t = new Lab1(A);

        Thread thread1 = new Thread(t.run1);
        Thread thread2 = new Thread(t.run2);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println("T1: " + t1);
        System.out.println("T1 size: " + t1.size());
        System.out.println("T2: " + t2);
        System.out.println("T2 size: " + t2.size());
    }

    private void t1Process() {
        for (int i = 0; i < A.size()/2 + 1; i ++) {
            int value = A.get(i);
            if (checkPerfectSquare(value)) {
                t1.add(value);
                System.out.print(value + " ");
            }
        }
    }

    private void t2Process() {
        for (int i = A.size()/2 + 1; i < A.size(); i ++) {
            int value = A.get(i);
            if (checkPerfectSquare(value)) {
                t2.add(value);
                System.out.print(value + " ");
            }
        }
    }

    private static boolean checkPerfectSquare(double x)
    {
        double sq = Math.sqrt(x);
        return ((sq - Math.floor(sq)) == 0);
    }
}