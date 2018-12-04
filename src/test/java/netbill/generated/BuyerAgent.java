package netbill.generated;

public abstract class BuyerAgent extends uk.ac.lancaster.scc.turtles.stellar.core.agent.BasicAgent {

	private final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier;
	private final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever retriever;
	private final uk.ac.lancaster.scc.turtles.stellar.core.util.IdentifierGenerator generator;

	protected BuyerAgent(
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier,
			final uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory history) {
		super(buildEndpoint(selfIdentifier, history));
		this.selfIdentifier = selfIdentifier;
		this.retriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(
				new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(
						history,
						NetBillBuyerProtocolSchema.instance(),
						new uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler()));
        this.generator = new uk.ac.lancaster.scc.turtles.stellar.core.util.IdentifierGenerator();
    }

	private static uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEndpoint buildEndpoint(
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier,
			final uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory history) {
		uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram.DatagramSender sender = new uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram.DatagramSender(2048);
		uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram.DatagramReceiver receiver = null;
		try {
			receiver = new uk.ac.lancaster.scc.turtles.stellar.core.communication.datagram.DatagramReceiver(Integer.parseInt(selfIdentifier.getPort()), 2048);
		} catch (java.net.SocketException e) {
			java.lang.System.err.println("Agent " + selfIdentifier.getName() + " cannot create the communication socket on port " + selfIdentifier.getPort());
			java.lang.System.exit(0);
		}
		uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler compiler = new uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler();
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever enactmentRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(history, NetBillBuyerProtocolSchema.instance(), compiler);
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever enabledRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(enactmentRetriever);
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker viabilityChecker = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker(enactmentRetriever);
		return new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEndpoint(sender, receiver, NetBillBuyerProtocolSchema.instance(), history, compiler, enabledRetriever, viabilityChecker);
	}

	@Override
	protected final void handleMessage(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message message) {
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage bsplMessage = (uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage) message;
        if (bsplMessage.getName().equals(QuoteSchema.instance().getName())) {
        	handleQuote(new Quote(bsplMessage));
        	return;
        }
        if (bsplMessage.getName().equals(GoodsSchema.instance().getName())) {
        	handleGoods(new Goods(bsplMessage));
        	return;
        }
        if (bsplMessage.getName().equals(ErrorSchema.instance().getName())) {
        	handleError(new Error(bsplMessage));
        	return;
        }
    }

	protected abstract void handleQuote(Quote receivedMessage);
	
	protected abstract void handleGoods(Goods receivedMessage);
	
	protected abstract void handleError(Error receivedMessage);
	
	public EnabledRfq retrieveEnabledRfq() {
		return new EnabledRfq(selfIdentifier, new java.util.HashMap<java.lang.String, java.lang.String>());
	}
	
	public void sendEnabledRfq(
			final EnabledRfq enabledMessage,
			final java.lang.String item,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(NetBillParameterName._pid.canonical(), generator.unique());
		viableBindings.put(NetBillParameterName._item.canonical(), item);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
	public java.util.List<EnabledAcceptQuote> retrieveEnabledAcceptQuote() {
		return retrieveEnabledAcceptQuote("");
	}
	
	public java.util.List<EnabledAcceptQuote> retrieveEnabledAcceptQuote(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(AcceptQuoteSchema.instance(), whereClause);
		java.util.List<EnabledAcceptQuote> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledAcceptQuote(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledAcceptQuote(
			final EnabledAcceptQuote enabledMessage,
			final java.lang.String quoteResponse,
			final java.lang.String ccard,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(NetBillParameterName._quoteResponse.canonical(), quoteResponse);
		viableBindings.put(NetBillParameterName._ccard.canonical(), ccard);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
	public java.util.List<EnabledRejectQuote> retrieveEnabledRejectQuote() {
		return retrieveEnabledRejectQuote("");
	}
	
	public java.util.List<EnabledRejectQuote> retrieveEnabledRejectQuote(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(RejectQuoteSchema.instance(), whereClause);
		java.util.List<EnabledRejectQuote> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledRejectQuote(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledRejectQuote(
			final EnabledRejectQuote enabledMessage,
			final java.lang.String quoteResponse,
			final java.lang.String outcome,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(NetBillParameterName._quoteResponse.canonical(), quoteResponse);
		viableBindings.put(NetBillParameterName._outcome.canonical(), outcome);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
}
