package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.Map;

public class Quote extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    Quote(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
            final Map<String, String> bindings) {
        super(QuoteSchema.instance().getName(), sender, receiver, bindings);
    }
    
    Quote(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

}
