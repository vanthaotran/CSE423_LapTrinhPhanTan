import java.lang.*;
import java.util.*;
import java.net.*;
import java.io.*;
public class Name {
    BufferedReader din; // buffer for data go into stream
    PrintStream pout;
    public void getSocket() throws IOException {
        Socket server = new Socket(Symbols.nameServer, Symbols.ServerPort); // initialize server
        din = new BufferedReader(new InputStreamReader(server.getInputStream()));
        pout = new PrintStream(server.getOutputStream());
    }
    public int insertName(String name, String hname, int portnum)
            throws IOException {
        getSocket(); // for getting the server's address (name+port)
        pout.println("insert " + name + " " + hname + " " + portnum);
        pout.flush();
        return Integer.parseInt(din.readLine()); // return buffer type of int
    }
    public PortAddr searchName(String name) throws IOException {
        getSocket(); // for getting the server's address (name+port)
        pout.println("search " + name);
        pout.flush(); // insert into stream, then flushing that stream
        String result = din.readLine(); // buffer for result output
        StringTokenizer st = new StringTokenizer(result); // break string into tokenization format
        int portnum = Integer.parseInt(st.nextToken()); // turn into int type
        String hname = st.nextToken(); // the next token of st
        return new PortAddr(hname, portnum); // get the host name + port
    }
    public static void main(String[] args) {
        Name myClient = new Name();
        try {
            myClient.insertName("hello1", "birch.ece.utexas.edu", 1000);
            PortAddr pa = myClient.searchName("hello1");
            System.out.println(pa.gethostname() + ":" + pa.getportnum());
        } catch (Exception e) {
            System.err.println("Server aborted:" + e);
        }
    }
}