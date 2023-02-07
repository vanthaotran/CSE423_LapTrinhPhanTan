import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;


// step 2: implement the remote interface
public class NameServiceImpl extends UnicastRemoteObject
        implements NameService {
    final int maxSize = 100;
    private String[] names = new String[maxSize];
    private String[] hosts = new String[maxSize];
    private int[] ports = new int[maxSize];
    private int dirsize = 0; // directory size

    public NameServiceImpl() throws RemoteException { // constructor
    }

//    @Override
//    public int search(String s) throws RemoteException {
//        for (int i = 0; i < dirsize; i++)
//            if (names[i].equals(s)) return i; // if search matching return directory's id
//        return -1; // if not return -1
//    }

//    @Override
//    public int insert(String s, String hostName, int portNumber)
//            throws RemoteException {
//        int oldIndex = search(s); // is it already there
//        if ((oldIndex == -1) && (dirsize < maxSize)) { // if new data ->
//            names[dirsize] = s;
//            hosts[dirsize] = hostName;
//            ports[dirsize] = portNumber;
//            dirsize++;
//            return 1; // write into and return 1
//        } else
//            return 0; // else return 0
//    }

//    @Override
//    public int getPort(int index) throws RemoteException {
//        return ports[index];
//    }
//
//    @Override
//    public String getHostName(int index) throws RemoteException {
//        return hosts[index];
//    }

    @Override
    public ArrayList<Integer> SapXep(ArrayList<Integer> arrayNumber) throws RemoteException {
        ArrayList<Integer> dayChan = new ArrayList<>();
        ArrayList<Integer> dayLe = new ArrayList<>();
        for (int i = 0; i < arrayNumber.size(); i++) {
            if(arrayNumber.get(i) %2 == 0){
                dayChan.add(arrayNumber.get(i));
            }
            else{
                dayLe.add(arrayNumber.get(i));
            }
        }
        ArrayList<Integer> outPutArray = new ArrayList<>();
        outPutArray.addAll(dayChan);
        outPutArray.addAll(dayLe);
        return outPutArray; // array type integer
    }

    @Override
    public boolean ChanLe(Integer number) throws RemoteException {
        if (number % 2 == 0) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) throws RemoteException, MalformedURLException, AlreadyBoundException {
        // create security manager
        System.setProperty("java.security.policy", "p.policy");
        try {
            NameServiceImpl nsi = new NameServiceImpl();
            LocateRegistry.createRegistry(1975);
            Naming.rebind("rmi://:1975/MyRMI", nsi);
            System.out.println("MyRMI bound in registry"); // open the registtry
        } catch (RemoteException e) {
            System.out.println("NameServiceImpl err: " + e.getMessage());
        }
    }
}
