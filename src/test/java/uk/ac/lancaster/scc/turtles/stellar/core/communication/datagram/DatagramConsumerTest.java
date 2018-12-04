package uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram;

import java.io.IOException;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatagramConsumerTest {

    public static TestPayloadSender sender = new TestPayloadSender();
    
    @Test
    public void startAndKillConsumerThread() throws InterruptedException, SocketException {
        DatagramConsumer consumer = new DatagramConsumer(49100, null, 512);
        Thread consumerThread = startThread(consumer);
        assertTrue(consumerThread.isAlive());
        consumer.kill();
        consumerThread.join();
    }
    
    @Test
    public void receiveAndKillConsumerThread() throws InterruptedException, SocketException, IOException {
//        BlockingQueue<byte[]> queue = new LinkedBlockingQueue<>();
//        DatagramConsumer consumer = new DatagramConsumer(49100, queue, 512);
//        Thread consumerThread = startThread(consumer);
//        sender.send("0", 49100);
//        byte[] payload = queue.take();
//        consumer.kill();
//        consumerThread.join();
//        assertArrayEquals("0".getBytes(StandardCharsets.ISO_8859_1), payload);
    }
    
    private Thread startThread(DatagramConsumer consumer) throws InterruptedException {
        Thread consumerThread = new Thread(consumer);
        consumerThread.setDaemon(true);
        consumerThread.start();
        Thread.sleep(100);
        return consumerThread;
    }
}