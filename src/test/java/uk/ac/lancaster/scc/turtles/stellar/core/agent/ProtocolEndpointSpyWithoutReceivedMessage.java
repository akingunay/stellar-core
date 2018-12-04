package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;

public class ProtocolEndpointSpyWithoutReceivedMessage extends ProtocolEndpointSpy {

        @Override
    public Message pollReceivedMessage() {
        if (!isStarted()) {
            throw new IllegalStateException("pollReceivedMessage() is called when adapter is not started.");
        }
        if (isStoped()) {
            throw new IllegalStateException("pollReceivedMessage() is called when adapter is stoped.");
        }
        increasePollCount();
        return null;
    }

    @Override
    public Message takeReceivedMessage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
