package client;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
        private DatagramSocket udpSocket;
        private String serverAddress;
        private int port;
        private Scanner scanner;

        private UDPClient(int port) throws IOException {
            this.port = port;
            this.udpSocket = new DatagramSocket();
            this.scanner = new Scanner(System.in);
        }

        private void start() throws IOException {
            String in = "GET /sample_dir/../../ HTTP/1.0\r\nUser-Agent: nc/0.0.1\r\nHost: 127.0.0.1\r\nAccept: */*\r\n\r\n";
            DatagramPacket p = new DatagramPacket(in.getBytes(), in.getBytes().length, InetAddress.getLocalHost(), port);
            this.udpSocket.send(p);

            byte[] receiveData = new byte[1024]; //TODO Look at byteBuffers as an alternative
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            udpSocket.receive(receivePacket);
            String responseString = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(responseString);
        }

        public static void main(String[] args) throws NumberFormatException, IOException {
            UDPClient sender = new UDPClient(3000);
            System.out.println("-- Running UDP Client at " + InetAddress.getLocalHost() + " --");
            sender.start();
        }

}
