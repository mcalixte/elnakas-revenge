package server;

import java.io.Serializable;
import java.net.InetAddress;

public class MyPacket implements Serializable {
    private static final long serialVersionUID = 1113799434508676095L;

    private int type;                 // Data, ACK, SYN, SYN-ACK, NAK
    private long sequenceNumber;
    private InetAddress peerAddress;
    private int peerPort;
    private byte[] payload;

    public int getType() {
        return type;
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public InetAddress getPeerAddress() {
        return peerAddress;
    }

    public int getPeerPort() {
        return peerPort;
    }

    public byte[] getPayload() {
        return payload;
    }

    public MyPacket(int type, long sequenceNumber, InetAddress peerAddress, int peerPort, byte[] payload) {
        this.type = type;
        this.sequenceNumber = sequenceNumber;
        this.peerAddress = peerAddress;
        this.peerPort = peerPort;
        this.payload = payload;
    }
}
