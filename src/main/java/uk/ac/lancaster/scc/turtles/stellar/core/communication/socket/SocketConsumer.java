package uk.ac.lancaster.scc.turtles.stellar.core.communication.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.BlockingQueue;

public class SocketConsumer implements Runnable {

    private final BlockingQueue<String> queue;
    private final ServerSocket serverSocket;
    
    public SocketConsumer(final int port, final BlockingQueue<String> queue) throws IOException {
        this.serverSocket = new ServerSocket(49100);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                (new Thread(new SocketConsumerThread(serverSocket.accept(), queue))).start();
            } catch (IOException e) {
                break;
            }
        } 
    }
    
    public void kill() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            
        }
    }
}
