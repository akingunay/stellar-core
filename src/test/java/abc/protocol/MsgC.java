package abc.protocol;

import java.util.Map;

public class MsgC extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    MsgC(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
            final Map<String, String> bindings) {
        super(MsgCSchema.instance().getName(), sender, receiver, bindings);
    }
    
    MsgC(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

    public String bindingOfId() {
        return getBinding(ABCParameterName._id.canonical());
    }
    
    public String bindingOfP2() {
        return getBinding(ABCParameterName._p2.canonical());
    }
    
    public String bindingOfP3() {
        return getBinding(ABCParameterName._p3.canonical());
    }
}
