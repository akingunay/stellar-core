package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Endpoint;

public abstract class BasicAgent extends Agent {

    private final Endpoint endpoint;
    private boolean executing;
    private boolean sendEnabled;
    
    protected BasicAgent(Endpoint endpoint) {
        this.endpoint = endpoint;
        this.executing = false;
        this.sendEnabled = false;
    }
    
    protected final void execute() {
        beforeStart();
        endpoint.start();
        sendEnabled = true;
        afterStart();
        executing = true;
        while (executing) {
            Message message = endpoint.pollReceivedMessage();
            if (message != null) {
                handleMessage(message);
            }
            if (executing) {
                act();
            }
        }
        beforeStop();
        sendEnabled = false;
        endpoint.stop();
        afterStop();
    }
    
    protected final void stop() {
        executing = false;
    }
    
    @Override
    protected final void emit(Message message) {
        if (sendEnabled) {
            endpoint.send(message);
        } else {
            throw new IllegalStateException("Message emission is disabled. Message emission is enabled only in afterStart(), handleMessage(Message), and beforeStop()");
        }
    }
    
    protected abstract void beforeStart();

    protected abstract void afterStart();

    protected abstract void act();

    protected abstract void beforeStop();
    
    protected abstract void afterStop();

}
