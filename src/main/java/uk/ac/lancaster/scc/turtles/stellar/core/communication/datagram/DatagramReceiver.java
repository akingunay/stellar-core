package uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram;

import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import uk.ac.lancaster.scc.turtles.stellar.core.communication.MessageReceiver;

public class DatagramReceiver implements MessageReceiver {

    private final BlockingQueue<byte[]> queue;
    private final Thread consumerThread;
    private final DatagramConsumer consumer;
    
    public DatagramReceiver(final int port, final int maxPayloadSize) throws SocketException {
        queue = new LinkedBlockingQueue<>();
        consumer = new DatagramConsumer(port, queue, maxPayloadSize);
        consumerThread = new Thread(consumer);
    }

    @Override
    public String pollWireMessage() {
        byte[] payload = queue.poll();
        return payload == null ? null : new String(payload, StandardCharsets.ISO_8859_1);
    }

    @Override
    public String takeWireMessage() throws InterruptedException {
        return new String(queue.take(), StandardCharsets.ISO_8859_1);
    }

    @Override
    public void start() {
        consumerThread.start();
    }

    @Override
    public void stop() {
        consumer.kill();
        try {
            consumerThread.join();
        } catch (InterruptedException e) {
            
        }
    }
    
}
