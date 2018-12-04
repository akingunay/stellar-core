package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import org.mockito.Mockito;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Endpoint;

public class BasicAgentResponsesThreeReceivedMessagesAndTerminates extends BasicAgentTestImp {

    private int responseCount;
    
    public BasicAgentResponsesThreeReceivedMessagesAndTerminates(Endpoint protocolEndpoint) {
        super(protocolEndpoint);
        responseCount = 0;
    }

    @Override
    protected void handleMessageBehavior() {
        emit(Mockito.mock(Message.class));
        responseCount++;
    }

    @Override
    protected void actBehavior() {
        if (responseCount == 3) {
            stop();
        }
    }
    
    

    
}
