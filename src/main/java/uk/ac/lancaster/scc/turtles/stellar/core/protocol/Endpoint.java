package uk.ac.lancaster.scc.turtles.stellar.core.protocol;

public interface Endpoint {

    void start();

    void stop();

    Message pollReceivedMessage();

    Message takeReceivedMessage();

    void send(final Message message);
    
}
