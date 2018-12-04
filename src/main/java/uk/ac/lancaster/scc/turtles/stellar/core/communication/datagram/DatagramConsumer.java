package uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

class DatagramConsumer implements Runnable {

    private final DatagramSocket socket;
    private final BlockingQueue<byte[]> packetQueue;
    private final int maxPayloadSize;

    DatagramConsumer(final int port, final BlockingQueue<byte[]> packetQueue, final int maxPayloadSize) throws SocketException {
        this.socket = new DatagramSocket(port);
        this.packetQueue = packetQueue;
        this.maxPayloadSize = maxPayloadSize;
    }

    @Override
    public void run() {
        byte[] payload = new byte[maxPayloadSize];
        DatagramPacket datagramPacket = new DatagramPacket(payload, payload.length);
        while (true) {
            try {
                socket.receive(datagramPacket);
                packetQueue.add(Arrays.copyOf(datagramPacket.getData(), datagramPacket.getLength()));
            } catch (IOException e) {
                break;
            }
        }
    }

    void kill() {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

}
