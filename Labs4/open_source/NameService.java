import java.rmi.*;
// step 1: define the remote interface
public interface NameService extends Remote { // step 1: set up the default of rmi programming
    public int search(String s) throws RemoteException;
    public int insert(String s, String hostName, int portNumber)
            throws RemoteException;
    public int getPort(int index) throws RemoteException;
    public String getHostName(int index) throws RemoteException;
}
