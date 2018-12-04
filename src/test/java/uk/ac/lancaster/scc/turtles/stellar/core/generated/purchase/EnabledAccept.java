package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.HashMap;
import java.util.Map;

public class EnabledAccept extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledAccept(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, final Map<String, String> bindings) {
        super(AcceptSchema.instance().getName(), sender, null, bindings);
    }

    Accept with(final Map<String, String> bindings, final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> newBindings = new HashMap<>(getBindings());
        newBindings.putAll(bindings);
        return new Accept(getSender(), receiver, newBindings);
    }
    
}
