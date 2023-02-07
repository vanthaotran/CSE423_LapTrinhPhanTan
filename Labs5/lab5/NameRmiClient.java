import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;
public class NameRmiClient {
    public static void main(String args[]) throws InterruptedException {
        try {
            NameService nS = (NameService)Naming.lookup("rmi://:1975/MyRMI");
            Random RandomNumber = new Random();
            int count, number, checkChanLe;
            while(true){
                count = RandomNumber.nextInt(10)+5;

                checkChanLe = RandomNumber.nextInt(100);

                ArrayList<Integer> arrayNumbers = new ArrayList<>(); // array for saving random numbers
//                System.out.print("Before: ");

                System.out.print("So can check: " + checkChanLe);
                for (int i = 0; i < count; i++) {
                    number = RandomNumber.nextInt(100);
                    arrayNumbers.add(number);
//                    System.out.print(arrayNumbers.get(i) +" ");
                }
                System.out.println();

                boolean soChanLe = nS.ChanLe(checkChanLe);
                if (soChanLe == true) System.out.print(checkChanLe + " la so chan");
                else System.out.print(checkChanLe + " la so le");


                ArrayList<Integer>arr = nS.SapXep(arrayNumbers);
//                System.out.print("After sorting: ");
                for (int i = 0; i < arr.size(); i++) {
//                    System.out.print(arr.get(i) +" ");
                }


                System.out.println("\n");
                Thread.sleep(2000);
            }
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.out.println(e);
        }
    }
}