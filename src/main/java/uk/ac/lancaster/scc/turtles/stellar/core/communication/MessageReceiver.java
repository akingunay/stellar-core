package uk.ac.lancaster.scc.turtles.stellar.core.communication;

public interface MessageReceiver {

    String pollWireMessage();

    String takeWireMessage() throws InterruptedException;

    void start();
    
    void stop();
    
}
