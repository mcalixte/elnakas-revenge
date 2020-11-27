package client;

import java.io.Serializable;
import java.net.InetAddress;

public class MyPacket implements Serializable {
    private static final long serialVersionUID = 1113799434508676095L;

    private final int type;                 // Data, ACK, SYN, SYN-ACK, NAK
    private final long sequenceNumber;
    private final InetAddress peerAddress;
    private final int peerPort;
    private final byte[] payload;

    public MyPacket(int type, long sequenceNumber, InetAddress peerAddress, int peerPort, byte[] payload) {
        this.type = type;
        this.sequenceNumber = sequenceNumber;
        this.peerAddress = peerAddress;
        this.peerPort = peerPort;
        this.payload = payload;
    }
}
