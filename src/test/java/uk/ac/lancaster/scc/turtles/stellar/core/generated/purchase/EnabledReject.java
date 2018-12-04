package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.HashMap;
import java.util.Map;

public class EnabledReject extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledReject(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, final Map<String, String> bindings) {
        super(RejectSchema.instance().getName(), sender, null, bindings);
    }

    Reject with(final Map<String, String> bindings, final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> newBindings = new HashMap<>(getBindings());
        newBindings.putAll(bindings);
        return new Reject(getSender(), receiver, newBindings);
    }

}
