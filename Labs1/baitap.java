//import java.util.Random;
//import java.util.ArrayList; // import the ArrayList class
//public class baitap extends Thread {
//    int N;
//    int[] A;
//
//    int[] T1_array;
//    int[] T2_array;
//
//    public baitap(int num) {
//        N = num;
//        A = new int[N];
//
//        Random rd = new Random(); // creating Random object
//        for (int i = 0; i < A.length; i++) {
//           A[i] = rd.nextInt(100 - 1) + 1;
////           System.out.println((A[i]));
//        }
//        this.N = N;
//        this.A = A;
//    }
//
//    public void T1() {
//        int i = 0;
//        while (i < A.length/2){
//            double sq = Math.sqrt(A[i]);
//            if(sq-Math.floor(sq) == 0) {
//                this.T1_array = append(T1_array, A[i]);
//            }
//
//            i++;
//        }
//    }
//
//    public void T2() {
//        int i = N/2 + 1;
//        while (i < A.length){
//            double sq = Math.sqrt(A[i]);
//            if(sq-Math.floor(sq) == 0) {
//                this.T1_array = append(T1_array, A[i]);
//            }
//
//            i++;
//        }
//    }
//
//    public static void main(String [] args) {
//        new baitap(50);
//
//
//    }
//
//
//
//}
