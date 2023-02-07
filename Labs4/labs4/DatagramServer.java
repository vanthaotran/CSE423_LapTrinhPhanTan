/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramServer {
    public static void main(String[] args) {
        DatagramPacket datapacket, returnpacket;
        int port = 2018;
        int len = 1024;
        try {
            DatagramSocket datasocket = new DatagramSocket(port);
            byte[] buf = new byte[len]; // bo dem
            datapacket = new DatagramPacket(buf, buf.length);
            while (true) {
                try {
//                    datasocket.receive(datapacket); // receive from client

                    String rs = new String(datapacket.getData(), 0, datapacket.getLength()); // get data
                    MyThread myThread = new MyThread(rs);
                    myThread.start();
                    myThread.join();
//                    byte[] dataReturn = myThread.getOut().getBytes();
//                    returnpacket = new DatagramPacket(
//                            dataReturn,
//                            datapacket.getLength(),
//                            datapacket.getAddress(),
//                            datapacket.getPort());
//                    datasocket.send(returnpacket);
                } catch (IOException e) {
                    System.err.println(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SocketException se) {
            System.err.println(se);
        }
    }
}
