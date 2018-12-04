package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import org.mockito.Mockito;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Endpoint;

public class BasicAgentSendsMessageInAfterStop extends BasicAgentTestImp {

    public BasicAgentSendsMessageInAfterStop(Endpoint protocolEndpoint) {
        super(protocolEndpoint);
    }

    @Override
    protected void actBehavior() {
        stop();
    }

    @Override
    protected void afterStopBehavior() {
        emit(Mockito.mock(Message.class));
    }

    
}
