package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.Map;

public class Rfq extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    Rfq(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
            final Map<String, String> bindings) {
        super(RfqSchema.instance().getName(), sender, receiver, bindings);
    }
    
    Rfq(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

}
