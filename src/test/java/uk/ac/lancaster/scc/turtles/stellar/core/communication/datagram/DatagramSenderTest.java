package uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatagramSenderTest {

//    @Test
//    public void sendPacket() throws InterruptedException, IOException {
//        BlockingQueue<byte[]> queue = new LinkedBlockingQueue<>();
//        DatagramConsumer consumer = new DatagramConsumer(49100, queue, 512);
//        Thread consumerThread = new Thread(consumer);
//        consumerThread.start();
//        Thread.sleep(100);
//        DatagramSender sender = new DatagramSender(512);
//        sender.send("0", new InetSocketAddress(49100));
//        assertArrayEquals("0".getBytes(StandardCharsets.ISO_8859_1), queue.take());
//        consumer.kill();
//        consumerThread.join();
//    }
//    
//    @Test(expected = IOException.class)
//    public void sendingLargerPayloadThanMaxSizeThrowsIOException() throws IOException {
//        DatagramSender sender = new DatagramSender(1);
//        sender.send("00", new InetSocketAddress(49100));
//    }
}
