package netbill.generated;

public class Error extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    Error(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
        super(ErrorSchema.instance().getName(), sender, receiver, bindings);
	}

    Error(uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

	public java.lang.String bindingOfPid() {
		return getBinding(NetBillParameterName._pid.canonical());
	}
	
	public java.lang.String bindingOfItem() {
		return getBinding(NetBillParameterName._item.canonical());
	}
	
	public java.lang.String bindingOfDenialReason() {
		return getBinding(NetBillParameterName._denialReason.canonical());
	}
	
	public java.lang.String bindingOfOutcome() {
		return getBinding(NetBillParameterName._outcome.canonical());
	}
	
}
