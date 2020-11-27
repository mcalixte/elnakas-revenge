package server;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class Server {
    public static void main(String[] args) {
        System.out.println("starting server");

        try {
            DatagramSocket serverSocket = new DatagramSocket(8007);
            System.out.println("opened socket");

//            byte[] buf = new byte[1024];
//            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
//            socket.receive(datagramPacket);
//            byte[] serialziedPacket = datagramPacket.getData();
//            System.out.println("received datagram packet");

//            MyPacket packet = (MyPacket) deserialize(serialziedPacket);
//            System.out.println("deserialized packet");
//
//            Request request = (Request) deserialize(packet.getPayload());
//            System.out.println("deserialized request");
//
//            System.out.println(request);

            byte[] buf = new byte[1024];
            DatagramPacket firstReceivedPacket = new DatagramPacket(buf, buf.length);
            serverSocket.receive(firstReceivedPacket);
            Packet packet = Packet.fromBuffer(ByteBuffer.wrap(firstReceivedPacket.getData()));
            Request request = (Request) deserialize(packet.getPayload());
//            Request request = (Request) deserialize(firstReceivedPacket.getData());
//            String message = new String(firstReceivedPacket.getData(), 0, firstReceivedPacket.getLength());
            System.out.println(request);


//            while(true) {
//                // receive first packet
//                byte[] buf = new byte[1024];
//                DatagramPacket firstReceivedPacket = new DatagramPacket(buf, buf.length);
//                serverSocket.receive(firstReceivedPacket);
//                String message = new String(firstReceivedPacket.getData(), 0, firstReceivedPacket.getLength());
//                System.out.println(message);
//            }
            // send response
//            String responseMessage = "hello from server445";
//            DatagramPacket response = new DatagramPacket(
//                    responseMessage.getBytes(), responseMessage.length(),
//                    firstReceivedPacket.getAddress(), firstReceivedPacket.getPort());
//            serverSocket.send(response);
//
//            // receive second packet
//            buf = new byte[1024];
//            DatagramPacket secondReceivedPacket = new DatagramPacket(buf, 1024);
//            serverSocket.receive(secondReceivedPacket);
//            String secondMessage = new String(secondReceivedPacket.getData(), 0, secondReceivedPacket.getLength());
//            System.out.println(secondMessage);

//            serverSocket.close();
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }

    public static byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Object deserialize(byte[] bytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
