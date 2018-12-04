package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Endpoint;

public class BasicAgentTerminatesWithoutReceivingMessage extends BasicAgentTestImp {

    public BasicAgentTerminatesWithoutReceivingMessage(Endpoint protocolEndpoint) {
        super(protocolEndpoint);
    }

    @Override
    protected void actBehavior() {
        stop();
    }
}
