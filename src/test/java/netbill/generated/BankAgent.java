package netbill.generated;

public abstract class BankAgent extends uk.ac.lancaster.scc.turtles.stellar.core.agent.BasicAgent {

	private final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier;
	private final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever retriever;
	private final uk.ac.lancaster.scc.turtles.stellar.core.util.IdentifierGenerator generator;

	protected BankAgent(
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier,
			final uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory history) {
		super(buildEndpoint(selfIdentifier, history));
		this.selfIdentifier = selfIdentifier;
		this.retriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(
				new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(
						history,
						NetBillBankProtocolSchema.instance(),
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
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever enactmentRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(history, NetBillBankProtocolSchema.instance(), compiler);
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever enabledRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(enactmentRetriever);
		uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker viabilityChecker = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker(enactmentRetriever);
		return new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEndpoint(sender, receiver, NetBillBankProtocolSchema.instance(), history, compiler, enabledRetriever, viabilityChecker);
	}

	@Override
	protected final void handleMessage(final uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message message) {
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage bsplMessage = (uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage) message;
        if (bsplMessage.getName().equals(ChargeSchema.instance().getName())) {
        	handleCharge(new Charge(bsplMessage));
        	return;
        }
    }

	protected abstract void handleCharge(Charge receivedMessage);
	
	public java.util.List<EnabledTransfer> retrieveEnabledTransfer() {
		return retrieveEnabledTransfer("");
	}
	
	public java.util.List<EnabledTransfer> retrieveEnabledTransfer(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(TransferSchema.instance(), whereClause);
		java.util.List<EnabledTransfer> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledTransfer(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledTransfer(
			final EnabledTransfer enabledMessage,
			final java.lang.String chargeResponse,
			final java.lang.String receipt,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(NetBillParameterName._chargeResponse.canonical(), chargeResponse);
		viableBindings.put(NetBillParameterName._receipt.canonical(), receipt);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
	public java.util.List<EnabledDeny> retrieveEnabledDeny() {
		return retrieveEnabledDeny("");
	}
	
	public java.util.List<EnabledDeny> retrieveEnabledDeny(final java.lang.String whereClause) {
		java.util.List<java.util.Map<java.lang.String, java.lang.String>> enabledBindings = retriever.retrieve(DenySchema.instance(), whereClause);
		java.util.List<EnabledDeny> enabledMessages = new java.util.ArrayList<>();
		for (java.util.Map<java.lang.String, java.lang.String> enabledBinding : enabledBindings) {
			enabledMessages.add(new EnabledDeny(selfIdentifier, enabledBinding));
		}
		return enabledMessages;
	}
	
	public void sendEnabledDeny(
			final EnabledDeny enabledMessage,
			final java.lang.String chargeResponse,
			final java.lang.String denialReason,
			final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
		java.util.Map<java.lang.String, java.lang.String> viableBindings = new java.util.HashMap<>();
		viableBindings.put(NetBillParameterName._chargeResponse.canonical(), chargeResponse);
		viableBindings.put(NetBillParameterName._denialReason.canonical(), denialReason);
		emit(enabledMessage.with(viableBindings, receiver));
	}
	
}
