
import java.io.IOException;
import java.net.*;
import java.util.Random;

public class DatagramClient {
    public static int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(100 - 10) + 10;
    }

    public static String getRandomSpace() { // sinh ra số random
        Random rand = new Random();
        int spacerSize = rand.nextInt(5 - 1) + 1;
        String spacer = "";
        for (int i = 0; i < spacerSize; i++) {
            spacer = spacer + " ";
        }
        return spacer;
    }

    public static String stringDataSend() { // gửi chuỗi string
        String integersString = Integer.toString(getRandomNumber()); // chuỗi = dãy số random
        for (int i = 0; i < 10; i++) {
            integersString = integersString + getRandomSpace() + getRandomNumber();
        }
        return integersString;
    }

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
            while (true) {
                try {
                    String integersString = stringDataSend();
                    String stringDataFromClient = integersString.replaceAll("\\s+", " ");
                    byte[] buffer = stringDataFromClient.getBytes(); //buffer co du lieu cua stringDataFromClient
                    // an array of bytes which is then used to create a DatagramPacket
                    System.out.println("Before: " + integersString);
                    sPacket = new DatagramPacket(buffer, buffer.length, ia, port);
                    datasocket.send(sPacket); // gui du lieu
                    byte[] rbuffer = new byte[len];
                    rPacket = new DatagramPacket(rbuffer, rbuffer.length); // receive
                    datasocket.receive(rPacket); //data nhan ve
                    String restring = new String(rPacket.getData());
                    System.out.println("After: " + restring);
                    Util.mySleep(2000);
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


