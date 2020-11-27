package client;

import java.io.*;
import java.net.*;
import java.nio.channels.DatagramChannel;

public class Client {

    public static void main(String[] args) {
        System.out.println("starting client");

        Request request = new Request();
        request.setType("GET");
        request.setUrl("/some_dir/some_file");
        request.setVersion("HTTP/1.0");
        request.setHeaders(new String[]{"User-Agent: IntelliJ", "Content-Type: text/html"});
        request.setBody("");
        System.out.println("created request");

        byte[] serializedRequest = serialize(request);
        System.out.println("serialized request");

        try {
            InetSocketAddress routerAddress = new InetSocketAddress(InetAddress.getByName("localhost"), 3000);
            InetSocketAddress serverAddress = new InetSocketAddress(InetAddress.getByName("localhost"), 8007);

            try (DatagramChannel channel = DatagramChannel.open()) {
                Packet p = new Packet.Builder()
                        .setType(0)
                        .setSequenceNumber(1L)
                        .setPortNumber(serverAddress.getPort())
                        .setPeerAddress(serverAddress.getAddress())
                        .setPayload(serialize(request))
                        .create();
                channel.send(p.toBuffer(), routerAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }

//            MyPacket packet = new MyPacket(0, 1L, serverAddress.getAddress(),
//                    serverAddress.getPort(), serializedRequest);
//            System.out.println("created MyPacket");
//
//            byte[] serializedPacket = serialize(packet);
//            System.out.println("serialized MyPacket");
//
//            try {
//                DatagramSocket socket = new DatagramSocket();
//                socket.setSoTimeout(3000);
//                System.out.println("opened socket");
//
//                if (serializedPacket != null) {
//                    DatagramPacket datagramPacket = new DatagramPacket(serializedPacket, serializedPacket.length,
//                            routerAddress.getAddress(), routerAddress.getPort());
//                    System.out.println("created datagram packet");
//
//                    socket.send(datagramPacket);
//                    System.out.print("sent datagram packet");
//                }
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }


//
//        try {
//            DatagramSocket clientSocket = new DatagramSocket();
//            clientSocket.setSoTimeout(3000);
//
//            // send request packet
////            byte[] serializedRequest = serialize(firstRequest);
//            DatagramPacket requestPacket = new DatagramPacket(
//                    packet.toBytes(), packet.toBytes().length,
//                    routerAddress.getAddress(), routerAddress.getPort());
//            clientSocket.send(requestPacket);
////
//////            // send first packet
//////            String firstMessage = "hello from client445";
//////            DatagramPacket firstPacket = new DatagramPacket(
//////                    firstMessage.getBytes(), firstMessage.length(),
//////                    InetAddress.getByName("localhost"), 4000);
//////            clientSocket.send(firstPacket);
//////
//////            while (true) {
//////                // receive response
//////                try {
//////                    byte[] serverResponse = new byte[1024];
//////                    DatagramPacket serverResponsePacket = new DatagramPacket(serverResponse, serverResponse.length);
//////                    clientSocket.receive(serverResponsePacket);
//////                    String serverResponseMessage = new String(serverResponsePacket.getData(), 0, serverResponsePacket.getLength());
//////                    System.out.println("received response from server: " + serverResponseMessage);
//////                }
//////                catch (SocketTimeoutException e) {
//////                    clientSocket.send(firstPacket);
//////                    continue;
//////                }
//////
//////            }
//////            // send second packet
//////            String secondMessage = "acknowledging hello from server445";
//////            DatagramPacket secondPacket = new DatagramPacket(
//////                    secondMessage.getBytes(), secondMessage.length(),
//////                    InetAddress.getByName("localhost"), 4000);
//////            clientSocket.send(secondPacket);
//////
//////            clientSocket.close();
//        }
//        catch(Exception e) {
//            System.out.println(Arrays.toString(e.getStackTrace()));
//
//        }

        }

    public static byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        }
        catch (IOException e) {
            System.out.println(e.getStackTrace());
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
            System.out.println("error when trying to deserialize object. class not found exception");
            return null;
        }
    }
}

