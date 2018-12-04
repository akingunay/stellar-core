package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Endpoint;

public abstract class ProtocolEndpointSpy implements Endpoint {

    private boolean started;
    private boolean stoped;
    private int pollCount;
    private int sendCount;
    
    public ProtocolEndpointSpy() {
        started = false;
        stoped = false;
        pollCount = 0;
        sendCount = 0;
    }
    
    @Override
    public void start() {
        if (started) {
            throw new IllegalStateException("start() is called when adapter is already started.");
        }
        started = true;
    }

    @Override
    public void stop() {
        if (!started) {
            throw new IllegalStateException("stop() is called when adapter is not started.");
        }
        stoped = true;
    }

    @Override
    public void send(Message message) {
        if (!started) {
            throw new IllegalStateException("send(Message) is called when adapter is not started.");
        }
        sendCount++;
    }

    protected void increasePollCount() {
        pollCount++;
    }
    
    int getPollCount() {
        return pollCount;
    }

    int getSendCount() {
        return sendCount;
    }

    boolean isStarted() {
        return started;
    }

    boolean isStoped() {
        return stoped;
    }
    
}
