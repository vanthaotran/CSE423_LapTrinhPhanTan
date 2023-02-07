import java.util.*;
public class NameTable {
    final int maxSize = 100;
    private String[] names = new String[maxSize];
    private String[] hosts = new String[maxSize];
    private int[] ports = new int[maxSize];
    private int dirsize = 0; // size of directory
    int search(String s) {
        for (int i = 0; i < dirsize; i++)
            if (names[i].equals(s)) return i; // if found: return stream's id in directory
        return -1; // if not found: -1
    }
    int insert(String s, String hostName, int portNumber) {
        int oldIndex = search(s); // is it already there
        if (oldIndex == -1) { // first time -> insert new row of data
            names[dirsize] = s;
            hosts[dirsize] = hostName;
            ports[dirsize] = portNumber;
            dirsize++;
            return 1;
        } else // no duplicate the oldl data
            return 0;
    }
    int getPort(int index) {
        return ports[index];
    }
    String getHostName(int index) {
        return hosts[index];
    }
}