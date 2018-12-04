package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.Map;

public class Delivery extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    Delivery(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
            final Map<String, String> bindings) {
        super(DeliverySchema.instance().getName(), sender, receiver, bindings);
    }
    
    Delivery(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

}
