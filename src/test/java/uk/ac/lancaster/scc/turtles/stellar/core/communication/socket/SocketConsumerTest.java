package uk.ac.lancaster.scc.turtles.stellar.core.communication.socket;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Test;
import static org.junit.Assert.*;

public class SocketConsumerTest {

    public static TestPayloadSender sender = new TestPayloadSender();

    @Test
    public void startAndKillConsumerThread() throws InterruptedException, IOException {
        SocketConsumer consumer = new SocketConsumer(49100, null);
        Thread consumerThread = startThread(consumer);
        assertTrue(consumerThread.isAlive());
        consumer.kill();
        consumerThread.join();
    }

    @Test
    public void receiveAndKillConsumerThread() throws InterruptedException, IOException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        SocketConsumer consumer = new SocketConsumer(49100, queue);
        Thread consumerThread = startThread(consumer);
        sender.send("0", "localhost", 49100);
        String payload = queue.take();
        consumer.kill();
        consumerThread.join();
        assertEquals("0", payload);
    }    
    
    private Thread startThread(SocketConsumer consumer) throws InterruptedException {
        Thread consumerThread = new Thread(consumer);
        consumerThread.setDaemon(true);
        consumerThread.start();
        Thread.sleep(100);
        return consumerThread;
    }
}