package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import org.mockito.Mockito;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;

public class ProtocolEndpointSpyWithReceivedMessage extends ProtocolEndpointSpy {

    @Override
    public Message pollReceivedMessage() {
        if (!isStarted()) {
            throw new IllegalStateException("pollReceivedMessage() is called when adapter is not started.");
        }
        if (isStoped()) {
            throw new IllegalStateException("pollReceivedMessage() is called when adapter is stoped.");
        }
        increasePollCount();
        Message message = Mockito.mock(Message.class);
        return message;
    }

    @Override
    public Message takeReceivedMessage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
