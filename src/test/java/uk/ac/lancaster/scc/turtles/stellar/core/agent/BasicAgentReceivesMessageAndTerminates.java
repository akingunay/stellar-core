package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Endpoint;

public class BasicAgentReceivesMessageAndTerminates extends BasicAgentTestImp {

    public BasicAgentReceivesMessageAndTerminates(Endpoint protocolEndpoint) {
        super(protocolEndpoint);
    }

    @Override
    public void handleMessageBehavior() {
        stop();
    }
}
