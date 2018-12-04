package netbill.generated;

public class EnabledRejectQuote extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledRejectQuote(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, 
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
		super(RejectQuoteSchema.instance().getName(), sender, null, bindings);
    }

	RejectQuote with(
			final java.util.Map<java.lang.String, java.lang.String> bindings,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> newBindings = new java.util.HashMap<>(getBindings());
		newBindings.putAll(bindings);
		return new RejectQuote(getSender(), receiver, newBindings);
	}

}
