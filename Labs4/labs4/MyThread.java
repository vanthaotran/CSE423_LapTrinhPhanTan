import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;

public class MyThread extends Thread {
    private String inputString = null;
    private static String output = ""; // sharing-variables

    DatagramSocket datasocket = new DatagramSocket(2018);


    public MyThread(String inputString) throws SocketException {
        this.inputString = inputString;
    }


    public void run() {
        convertStringToArray(inputString); // xu ly chuoi

        try {
            returnForClient(datasocket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public String getOut() {
        return output;
    }

    public static void returnForClient (DatagramSocket datasocket) throws IOException {

        DatagramPacket datapacket, returnpacket;
        datasocket = new DatagramSocket(2018);
        byte[] buf = new byte[1024];
        datapacket = new DatagramPacket(buf, buf.length);
        MyThread myThread = null;
        byte[] dataReturn = myThread.getOut().getBytes();
        returnpacket = new DatagramPacket(
                dataReturn,
                datapacket.getLength(),
                datapacket.getAddress(),
                datapacket.getPort());
        datasocket.send(returnpacket);

    }

    public static void convertStringToArray(String inputArray) {
        ArrayList<Integer> evenSequence = new ArrayList<>(); // ham lưu số lẻ //
        ArrayList<Integer> oddSequence = new ArrayList<>(); // hàm lưu số chẵn

        String stringRemoveDuplicateSpace = inputArray.replaceAll("\\s+", " "); //xoá đi ký tự 2 lần space
        String[] arrayRemoveDuplicateSpace = stringRemoveDuplicateSpace.split("\\s"); // turn into array // chuyển thành mảng

        for (int i = 0; i < arrayRemoveDuplicateSpace.length; i++) {
            if (Integer.parseInt(arrayRemoveDuplicateSpace[i]) % 2 == 0) {
                evenSequence.add(Integer.parseInt(arrayRemoveDuplicateSpace[i])); //
            } else {
                oddSequence.add(Integer.parseInt(arrayRemoveDuplicateSpace[i]));
            }
        }

        for (int i = 0; i < evenSequence.size(); i++) {
            output = output + evenSequence.get(i) + " ";
        }
        for (int i = evenSequence.size(); i < evenSequence.size() + oddSequence.size(); i++) {
            output = output + oddSequence.get(i - evenSequence.size()) + " ";
        }

    }


}
