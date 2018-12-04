package netbill.generated;

public class EnabledGoods extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage {

    EnabledGoods(
    		final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier sender, 
    		final java.util.Map<java.lang.String, java.lang.String> bindings) {
		super(GoodsSchema.instance().getName(), sender, null, bindings);
    }

	Goods with(
			final java.util.Map<java.lang.String, java.lang.String> bindings,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> newBindings = new java.util.HashMap<>(getBindings());
		newBindings.putAll(bindings);
		return new Goods(getSender(), receiver, newBindings);
	}

}
