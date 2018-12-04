package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.Map;

public class Accept extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    Accept(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
            final Map<String, String> bindings) {
        super(AcceptSchema.instance().getName(), sender, receiver, bindings);
    }
    
    Accept(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

}
