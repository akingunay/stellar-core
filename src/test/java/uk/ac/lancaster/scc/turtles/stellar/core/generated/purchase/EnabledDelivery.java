package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.HashMap;
import java.util.Map;

public class EnabledDelivery extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledDelivery(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, final Map<String, String> bindings) {
        super(DeliverySchema.instance().getName(), sender, null, bindings);
    }

    Delivery with(final Map<String, String> bindings, final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> newBindings = new HashMap<>(getBindings());
        newBindings.putAll(bindings);
        return new Delivery(getSender(), receiver, newBindings);
    }
    

}
