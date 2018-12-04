package abc.protocol;

import java.util.Map;

public class MsgB extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    MsgB(final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
            final Map<String, String> bindings) {
        super(MsgBSchema.instance().getName(), sender, receiver, bindings);
    }
    
    MsgB(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

    public String bindingOfId() {
        return getBinding(ABCParameterName._id.canonical());
    }
    
    public String bindingOfP1() {
        return getBinding(ABCParameterName._p1.canonical());
    }
    
    public String bindingOfP2() {
        return getBinding(ABCParameterName._p2.canonical());
    }
}

