package abc.protocol;

import java.util.HashMap;
import java.util.Map;

public class EnabledMsgA extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledMsgA(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, final Map<String, String> bindings) {
        super(MsgASchema.instance().getName(), sender, null, bindings);
    }

    MsgA with(final Map<String, String> bindings, final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> newBindings = new HashMap<>(getBindings());
        newBindings.putAll(bindings);
        return new MsgA(getSender(), receiver, newBindings);
    }
}
