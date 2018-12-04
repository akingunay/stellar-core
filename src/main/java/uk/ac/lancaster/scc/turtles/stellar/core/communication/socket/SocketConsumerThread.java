package uk.ac.lancaster.scc.turtles.stellar.core.communication.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class SocketConsumerThread implements Runnable {
 
    private final Socket socket;
    private final BlockingQueue<String> queue;

    public SocketConsumerThread(final Socket socket, final BlockingQueue<String> queue) {
        this.socket = socket;
        this.queue = queue;
    }
    
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            queue.add(in.readLine());
            socket.close();
        } catch (IOException ex) {

        }
    }
    
}
