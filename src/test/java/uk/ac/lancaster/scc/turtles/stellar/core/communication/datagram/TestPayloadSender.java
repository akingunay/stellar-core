package uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

class TestPayloadSender {

    public void send(String payload, int port) throws SocketException, IOException {
        byte[] payloadBytes = payload.getBytes(StandardCharsets.ISO_8859_1);
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.send(new DatagramPacket(payloadBytes, payloadBytes.length, new InetSocketAddress(port)));
        }
    }
}
