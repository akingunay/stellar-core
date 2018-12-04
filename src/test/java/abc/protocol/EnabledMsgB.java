package abc.protocol;

import java.util.HashMap;
import java.util.Map;

public class EnabledMsgB extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledMsgB(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, final Map<String, String> bindings) {
        super(MsgBSchema.instance().getName(), sender, null, bindings);
    }

    MsgB with(final Map<String, String> bindings, final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> newBindings = new HashMap<>(getBindings());
        newBindings.putAll(bindings);
        return new MsgB(getSender(), receiver, newBindings);
    }
    
}
