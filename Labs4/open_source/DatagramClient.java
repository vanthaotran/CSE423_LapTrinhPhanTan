import java.net.*;
import java.io.*;
public class DatagramClient {
    public static void main(String[] args) {
        String hostname;
        int port = 2018;
        int len = 1024;
        DatagramPacket sPacket, rPacket;
        if (args.length > 0)
            hostname = args[0];
        else
            hostname = "localhost";
        try {
            InetAddress ia = InetAddress.getByName(hostname); // determine the address of internet (name/ip..)
            DatagramSocket datasocket = new DatagramSocket(); // sending point/receiving point for a disconnect delivery packet
            BufferedReader stdinp = new BufferedReader(
                    new InputStreamReader(System.in));

            while (true) {
                try {
                    String echoline = stdinp.readLine(); // read by buffering (in a thread)
                    if (echoline.equals("done")) break; //
                    byte[] buffer = new byte[echoline.length()]; // an array of bytes which is then used to create a DatagramPacket
                    buffer = echoline.getBytes();

                    sPacket = new DatagramPacket(buffer, buffer.length, ia, port);
                    datasocket.send(sPacket); // send the respond
                    byte[] rbuffer = new byte[len];
                    rPacket = new DatagramPacket(rbuffer, rbuffer.length);
                    datasocket.receive(rPacket);
                    String retstring = new String(rPacket.getData());
                    System.out.println(retstring);
                } catch (IOException e) {
                    System.err.println(e);
                }
            } // while
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (SocketException se) {
            System.err.println(se);
        }
    }  // end main
}