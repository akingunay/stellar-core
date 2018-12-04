package netbill.generated;

public class EnabledCharge extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledCharge(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, 
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
		super(ChargeSchema.instance().getName(), sender, null, bindings);
    }

	Charge with(
			final java.util.Map<java.lang.String, java.lang.String> bindings,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> newBindings = new java.util.HashMap<>(getBindings());
		newBindings.putAll(bindings);
		return new Charge(getSender(), receiver, newBindings);
	}

}
