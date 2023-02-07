import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// step 1: set up interface : define the remote interface
public interface NameService extends Remote {
//    public int search(String s) throws RemoteException;
//    public int insert(String s, String hostName, int portNumber)
//            throws RemoteException;
//    public int getPort(int index) throws RemoteException;
//    public String getHostName(int index) throws RemoteException;
    public ArrayList<Integer> SapXep(ArrayList<Integer> arrayNumber) throws RemoteException;

    public boolean ChanLe(Integer num) throws  RemoteException;
}
