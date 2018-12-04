package uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import uk.ac.lancaster.scc.turtles.stellar.core.communication.MessageSender;

public class DatagramSender implements MessageSender {

    private final int maxPayloadSize;

    public DatagramSender(int maxPayloadSize) {
        this.maxPayloadSize = maxPayloadSize;
    }
    
    @Override
    public void send(final String payload, final InetSocketAddress receipent) throws IOException {
        byte[] payloadBytes = payload.getBytes(StandardCharsets.ISO_8859_1);
        if (payloadBytes.length <= maxPayloadSize) {
            try (DatagramSocket socket = new DatagramSocket()) {
                socket.send(new DatagramPacket(payloadBytes, payloadBytes.length, receipent));
            }
        } else {
            throw new IOException("Size of the payload (" + payloadBytes.length + ") must be less than " + maxPayloadSize + ".");
        }
    }

}
