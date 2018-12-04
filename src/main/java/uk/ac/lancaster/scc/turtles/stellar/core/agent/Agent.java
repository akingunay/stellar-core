package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;

public abstract class Agent {

    protected abstract void handleMessage(Message message);
    
    protected abstract void emit(Message message);
    
}
