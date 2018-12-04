package netbill.generated;

public abstract class SellerAgent extends uk.ac.lancaster.scc.turtles.stellar.core.agent.BasicAgent {

	private final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier;
	private final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever retriever;
	private final uk.ac.lancaster.scc.turtles.stellar.core.util.IdentifierGenerator generator;

	protected SellerAgent(
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier,
			final uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory history) {
		super(buildEndpoint(selfIdentifier, history));
		this.selfIdentifier = selfIdentifier;
		this.retriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(
				new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(
						history,
						NetBillSellerProtocolSchema.instance(),
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
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever enactmentRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(history, NetBillSellerProtocolSchema.instance(), compiler);
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever enabledRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(enactmentRetriever);
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker viabilityChecker = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker(enactmentRetriever);
		return new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEndpoint(sender, receiver, NetBillSellerProtocolSchema.instance(), history, compiler, enabledRetriever, viabilityChecker);
	}

	@Override
	protected final void handleMessage(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message message) {
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage bsplMessage = (uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage) message;
        if (bsplMessage.getName().equals(RfqSchema.instance().getName())) {
        	handleRfq(new Rfq(bsplMessage));
        	return;
        }
        if (bsplMessage.getName().equals(AcceptQuoteSchema.instance().getName())) {
        	handleAcceptQuote(new AcceptQuote(bsplMessage));
        	return;
        }
        if (bsplMessage.getName().equals(RejectQuoteSchema.instance().getName())) {
        	handleRejectQuote(new RejectQuote(bsplMessage));
        	return;
        }
        if (bsplMessage.getName().equals(TransferSchema.instance().getName())) {
        	handleTransfer(new Transfer(bsplMessage));
        	return;
        }
        if (bsplMessage.getName().equals(DenySchema.instance().getName())) {
        	handleDeny(new Deny(bsplMessage));
        	return;
        }
    }

	protected abstract void handleRfq(Rfq receivedMessage);
	
	protected abstract void handleAcceptQuote(AcceptQuote receivedMessage);
	
	protected abstract void handleRejectQuote(RejectQuote receivedMessage);
	
	protected abstract void handleTransfer(Transfer receivedMessage);
	
	protected abstract void handleDeny(Deny receivedMessage);
	
	public java.util.List<EnabledQuote> retrieveEnabledQuote() {
		return retrieveEnabledQuote("");
	}
	
	public java.util.List<EnabledQuote> retrieveEnabledQuote(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(QuoteSchema.instance(), whereClause);
		java.util.List<EnabledQuote> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledQuote(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledQuote(
			final EnabledQuote enabledMessage,
			final java.lang.String price,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(NetBillParameterName._price.canonical(), price);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
	public java.util.List<EnabledCharge> retrieveEnabledCharge() {
		return retrieveEnabledCharge("");
	}
	
	public java.util.List<EnabledCharge> retrieveEnabledCharge(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(ChargeSchema.instance(), whereClause);
		java.util.List<EnabledCharge> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledCharge(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledCharge(
			final EnabledCharge enabledMessage,
			final java.lang.String account,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(NetBillParameterName._account.canonical(), account);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
	public java.util.List<EnabledGoods> retrieveEnabledGoods() {
		return retrieveEnabledGoods("");
	}
	
	public java.util.List<EnabledGoods> retrieveEnabledGoods(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(GoodsSchema.instance(), whereClause);
		java.util.List<EnabledGoods> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledGoods(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledGoods(
			final EnabledGoods enabledMessage,
			final java.lang.String outcome,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(NetBillParameterName._outcome.canonical(), outcome);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
	public java.util.List<EnabledError> retrieveEnabledError() {
		return retrieveEnabledError("");
	}
	
	public java.util.List<EnabledError> retrieveEnabledError(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(ErrorSchema.instance(), whereClause);
		java.util.List<EnabledError> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledError(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledError(
			final EnabledError enabledMessage,
			final java.lang.String outcome,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(NetBillParameterName._outcome.canonical(), outcome);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
}
