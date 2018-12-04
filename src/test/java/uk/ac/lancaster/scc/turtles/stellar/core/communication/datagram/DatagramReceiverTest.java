package uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatagramReceiverTest {

    public static TestPayloadSender sender = new TestPayloadSender();
    
    @Test
    public void pollWireMessageWhenMessageReceived() throws InterruptedException, IOException {
//        DatagramReceiver receiver = new DatagramReceiver(49100, 512);
//        receiver.start();
//        Thread.sleep(100);
//        sender.send("0", 49100);
//        Thread.sleep(100);
//        assertEquals("0", receiver.pollWireMessage());
//        receiver.stop();
    }

    @Test
    public void pollWireMessageWhenMessageNotReceived() throws InterruptedException, IOException {
//        DatagramReceiver receiver = new DatagramReceiver(49100, 512);
//        receiver.start();
//        assertNull(receiver.pollWireMessage());
//        receiver.stop();
    }

    @Test
    public void takeWireMessageWhenMessageReceived() throws InterruptedException, IOException {
//        DatagramReceiver receiver = new DatagramReceiver(49100, 512);
//        receiver.start();
//        Thread.sleep(100);
//        sender.send("0", 49100);
//        Thread.sleep(100);
//        assertEquals("0", receiver.takeWireMessage());
//        receiver.stop();
    }
    
}