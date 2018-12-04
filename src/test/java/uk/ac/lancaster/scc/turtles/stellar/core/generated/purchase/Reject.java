package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.Map;

public class Reject extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    Reject(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
            final Map<String, String> bindings) {
        super(RejectSchema.instance().getName(), sender, receiver, bindings);
    }
    
    Reject(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

}
