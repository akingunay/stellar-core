package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import org.mockito.Mockito;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Endpoint;

public class BasicAgentSendsMessageInBeforeStart extends BasicAgentTestImp {

    public BasicAgentSendsMessageInBeforeStart(Endpoint protocolEndpoint) {
        super(protocolEndpoint);
    }

    @Override
    protected void beforeStartBehavior() {
        emit(Mockito.mock(Message.class));
    }

    
}
