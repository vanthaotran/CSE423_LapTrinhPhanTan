import java.net.*;
import java.io.*;
import java.util.*;
public class NameServer {
    NameTable table; // sharing-variables
    public NameServer() { // constructor func ()
        table = new NameTable();
    }
    void handleclient(Socket theClient) {
        try {
            BufferedReader din = new BufferedReader
                    (new InputStreamReader(theClient.getInputStream())); // din from client
            PrintWriter pout = new PrintWriter(theClient.getOutputStream()); // pout from client
            String getline = din.readLine(); // read the info
            StringTokenizer st = new StringTokenizer(getline); // turn to tokenizer
            String tag = st.nextToken(); // the next character of that tokenizer
            if (tag.equals("search")) { // if client wants to search
                int index = table.search(st.nextToken());
                if (index == -1) // not found
                    pout.println(-1 + " " + "nullhost"); // not found in the registry

                else // return the id of that finding keyword matching the id of directory
                    pout.println(table.getPort(index) + " "
                            + table.getHostName(index)); // return port, host name (id in directory)
            } else if (tag.equals("insert")) { // if client wants to insert
                String name = st.nextToken(); // get the next name's token character
                String hostName = st.nextToken(); // ...
                int port = Integer.parseInt(st.nextToken()); // port = int(token)
                int retValue = table.insert(name, hostName, port);
                pout.println(retValue); // write output data in readable text
            }
            pout.flush(); // insert into stream, then flushing that stream
            // flush is used in only inserting (writing into)
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public static void main(String[] args) {
        NameServer ns = new NameServer();
        System.out.println("NameServer started:");
        try {
            ServerSocket listener = new ServerSocket(Symbols.ServerPort); // listen the socket
            while (true) {
                Socket aClient = listener.accept(); // listen
                ns.handleclient(aClient);
                aClient.close(); // done -> close
            }
        } catch (IOException e) {
            System.err.println("Server aborted:" + e); // cancel for ...
        }
    }
}