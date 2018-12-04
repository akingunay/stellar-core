package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import org.mockito.Mockito;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Endpoint;

public class BasicAgentSendsMessageInAfterStartAndActAndBeforeStop extends BasicAgentTestImp {

    private final static Message message = Mockito.mock(Message.class);
    
    public BasicAgentSendsMessageInAfterStartAndActAndBeforeStop(Endpoint protocolAdapter) {
        super(protocolAdapter);
    }

    @Override
    protected void afterStartBehavior() {
        emit(message);
    }

    @Override
    protected void actBehavior() {
        emit(message);
        stop();
    }

    @Override
    protected void beforeStopBehavior() {
        emit(message);
    }

    
}
