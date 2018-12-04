package uk.ac.lancaster.scc.turtles.stellar.core.communication;

import java.io.IOException;
import java.net.InetSocketAddress;

public interface MessageSender {
    
    void send(String payload, InetSocketAddress receipent) throws IOException;
    
}