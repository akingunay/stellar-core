package netbill.generated;

public class EnabledDeny extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledDeny(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, 
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
		super(DenySchema.instance().getName(), sender, null, bindings);
    }

	Deny with(
			final java.util.Map<java.lang.String, java.lang.String> bindings,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> newBindings = new java.util.HashMap<>(getBindings());
		newBindings.putAll(bindings);
		return new Deny(getSender(), receiver, newBindings);
	}

}
