package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import org.mockito.Mockito;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Endpoint;

public class BasicAgentResponsesReceivedMessageAndTerminates extends BasicAgentTestImp {

    public BasicAgentResponsesReceivedMessageAndTerminates(Endpoint protocolEndpoint) {
        super(protocolEndpoint);
    }

    @Override
    protected void handleMessageBehavior() {
        emit(Mockito.mock(Message.class));
        stop();
    }

    
}
