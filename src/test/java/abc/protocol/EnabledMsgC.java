package abc.protocol;

import java.util.HashMap;
import java.util.Map;

public class EnabledMsgC extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledMsgC(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, final Map<String, String> bindings) {
        super(MsgCSchema.instance().getName(), sender, null, bindings);
    }

    MsgC with(final Map<String, String> bindings, final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> newBindings = new HashMap<>(getBindings());
        newBindings.putAll(bindings);
        return new MsgC(getSender(), receiver, newBindings);
    }
    
}