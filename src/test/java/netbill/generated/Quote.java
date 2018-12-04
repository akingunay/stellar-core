package netbill.generated;

public class Quote extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    Quote(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
        super(QuoteSchema.instance().getName(), sender, receiver, bindings);
	}

    Quote(uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

	public java.lang.String bindingOfPid() {
		return getBinding(NetBillParameterName._pid.canonical());
	}
	
	public java.lang.String bindingOfItem() {
		return getBinding(NetBillParameterName._item.canonical());
	}
	
	public java.lang.String bindingOfPrice() {
		return getBinding(NetBillParameterName._price.canonical());
	}
	
}
