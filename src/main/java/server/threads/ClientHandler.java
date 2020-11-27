//package server.threads;
//
//import common.Request;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.net.*;
//
//public class ClientHandler implements Runnable{
//
//    private DatagramSocket socket = null;
//    private DatagramPacket receivedPacket = null;
//    private int threadID;
//
//    public ClientHandler(DatagramSocket socket, DatagramPacket packet, int threadID) {
//        this.socket = socket;
//        this.receivedPacket = packet;
//        this.threadID = threadID;
//    }
//
//    @Override
//    public void run() {
//        try {
//            String httpRequestString = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
//            System.out.println("MKC0: Request received:"+ httpRequestString);
//            Request request = new Request(httpRequestString);
//            byte[] data = makeResponse(RequestHandler.handleRequest(request));
//            DatagramPacket response = new DatagramPacket(data, data.length, receivedPacket.getAddress(), receivedPacket.getPort());
//            socket.send(response);
//
//            byte[] buffer = new byte[1024];
//            DatagramPacket packet =  new DatagramPacket(buffer, buffer.length);
//            socket.receive(packet);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private byte[] makeResponse(String httpResponseString) {
//        byte[] data = null;
//        try {
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ObjectOutputStream os = new ObjectOutputStream(outputStream);
//            os.writeObject(httpResponseString);
//            data = outputStream.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return data;
//    }
//}
