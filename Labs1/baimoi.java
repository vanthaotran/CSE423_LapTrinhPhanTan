import java.util.ArrayList;
import java.util.Random;

public class baimoi extends Thread{
    int N;
    ArrayList<Integer> A = new ArrayList<Integer>(N);
    
    ArrayList<Integer> T1_array = new ArrayList<Integer>();
    ArrayList<Integer> T2_array = new ArrayList<Integer>();

    public baimoi(int num) {
        N = num;
        ArrayList<Integer> A = new ArrayList<Integer>(N);
        this.A = A;
        this.N = N;
        Random random = new Random();
        int i;
        for(i = 0; i < N; i++) {
            A.add(random.nextInt(100 - 1) + 1);
        }
        System.out.println(A);
    }

    public void T1() {
        int i = 0;
        for(int item : this.A){
            double sq = Math.sqrt(item);
            if(sq-Math.floor(sq) == 0) {
                this.T1_array.add(item);
            }
        }

//        while (i < N/2){
//            double sq = Math.sqrt(A[i]);
//            if(sq-Math.floor(sq) == 0) {
//                this.T1_array = append(T1_array, A[i]);
//            }
//
//            i++;
//        }
    }
        public void T2() {
        int i = N/2 + 1;
            for(int item : A){
                double sq = Math.sqrt(item);
                if(sq-Math.floor(sq) == 0) {
                    this.T2_array.add(item);
                }
            }
    }

    public static void main(String [] args) {

//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter the number of elements: ");

        baimoi myArray = new baimoi(10);
        System.out.print(myArray.T1_array);

    }

}
