package netbill.generated;

public class Deny extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    Deny(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender,
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver,
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
        super(DenySchema.instance().getName(), sender, receiver, bindings);
	}

    Deny(uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage message) {
        super(message);
    }

	public java.lang.String bindingOfPid() {
		return getBinding(NetBillParameterName._pid.canonical());
	}
	
	public java.lang.String bindingOfPrice() {
		return getBinding(NetBillParameterName._price.canonical());
	}
	
	public java.lang.String bindingOfCcard() {
		return getBinding(NetBillParameterName._ccard.canonical());
	}
	
	public java.lang.String bindingOfAccount() {
		return getBinding(NetBillParameterName._account.canonical());
	}
	
	public java.lang.String bindingOfChargeResponse() {
		return getBinding(NetBillParameterName._chargeResponse.canonical());
	}
	
	public java.lang.String bindingOfDenialReason() {
		return getBinding(NetBillParameterName._denialReason.canonical());
	}
	
}
