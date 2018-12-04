package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.HashMap;
import java.util.Map;

public class EnabledQuote extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledQuote(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, final Map<String, String> bindings) {
        super(QuoteSchema.instance().getName(), sender, null, bindings);
    }

    Quote with(final Map<String, String> bindings, final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> newBindings = new HashMap<>(getBindings());
        newBindings.putAll(bindings);
        return new Quote(getSender(), receiver, newBindings);
    }
    
}
