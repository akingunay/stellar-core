package netbill.generated;

public class RejectQuote extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    RejectQuote(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
        super(RejectQuoteSchema.instance().getName(), sender, receiver, bindings);
	}

    RejectQuote(uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
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
	
	public java.lang.String bindingOfQuoteResponse() {
		return getBinding(NetBillParameterName._quoteResponse.canonical());
	}
	
	public java.lang.String bindingOfOutcome() {
		return getBinding(NetBillParameterName._outcome.canonical());
	}
	
}
