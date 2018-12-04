package netbill.generated;

public class Goods extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    Goods(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
        super(GoodsSchema.instance().getName(), sender, receiver, bindings);
	}

    Goods(uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

	public java.lang.String bindingOfPid() {
		return getBinding(NetBillParameterName._pid.canonical());
	}
	
	public java.lang.String bindingOfItem() {
		return getBinding(NetBillParameterName._item.canonical());
	}
	
	public java.lang.String bindingOfReceipt() {
		return getBinding(NetBillParameterName._receipt.canonical());
	}
	
	public java.lang.String bindingOfOutcome() {
		return getBinding(NetBillParameterName._outcome.canonical());
	}
	
}
