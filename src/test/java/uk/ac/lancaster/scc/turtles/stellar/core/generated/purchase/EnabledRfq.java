package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.HashMap;
import java.util.Map;

public class EnabledRfq extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledRfq(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, final Map<String, String> bindings) {
        super(RfqSchema.instance().getName(), sender, null, bindings);
    }

    Rfq with(final Map<String, String> bindings, final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> newBindings = new HashMap<>(getBindings());
        newBindings.putAll(bindings);
        return new Rfq(getSender(), receiver, newBindings);
    }

}


