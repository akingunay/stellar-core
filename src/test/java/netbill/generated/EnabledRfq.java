package netbill.generated;

public class EnabledRfq extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledRfq(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, 
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
		super(RfqSchema.instance().getName(), sender, null, bindings);
    }

	Rfq with(
			final java.util.Map<java.lang.String, java.lang.String> bindings,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> newBindings = new java.util.HashMap<>(getBindings());
		newBindings.putAll(bindings);
		return new Rfq(getSender(), receiver, newBindings);
	}

}
