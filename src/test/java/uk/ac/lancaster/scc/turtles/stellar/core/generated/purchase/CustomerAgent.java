package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CustomerAgent extends uk.ac.lancaster.scc.turtles.stellar.core.agent.BasicAgent {
    
    private final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier;
    private final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever retriever;
    private final uk.ac.lancaster.scc.turtles.stellar.core.util.IdentifierGenerator generator;

    protected CustomerAgent(
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier, 
            final uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory history) {
        super(buildEndpoint(selfIdentifier, history));
        this.selfIdentifier = selfIdentifier;
        this.retriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(
                new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(
                        history, PurchaseSchema.instance(),
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
        } catch (SocketException e) {
            System.err.println("Agent " + selfIdentifier.getName() + " cannot create the communication socket on port " + selfIdentifier.getPort());
            System.exit(0);
        }
        uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler compiler = new uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler();
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever enactmentRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(history, PurchaseSchema.instance(), compiler);
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever enabledRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(enactmentRetriever);
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker viabilityChecker = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker(enactmentRetriever);
        return new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEndpoint(sender, receiver, PurchaseSchema.instance(), history, compiler, enabledRetriever, viabilityChecker);
    }
    
    @Override
    protected final void handleMessage(
            final uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message message) {
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage bsplMessage = (uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage) message;
        if (bsplMessage.getName().equals(QuoteSchema.instance().getName())) {
            handleQuote(new Quote(bsplMessage));
            return;
        }
        if (bsplMessage.getName().equals(DeliverySchema.instance().getName())) {
            handleDelivery(new Delivery(bsplMessage));
            return;
        }
    }

    public EnabledRfq retrieveEnabledRfq() {
        return new EnabledRfq(selfIdentifier, new HashMap<>());
    }

    public void sendEnabledRfq(
            final EnabledRfq enabledMessage,
            final String item,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> viableBindings = new HashMap<>();
        viableBindings.put(PurchaseParameterName._id.canonical(), generator.unique());
        viableBindings.put(PurchaseParameterName._item.canonical(), item);
        emit(enabledMessage.with(viableBindings, receiver));
    }
    
    public List<EnabledAccept> retrieveEnabledAccepts(
            final String whereClause) {
        List<Map<String, String>> enabledBindings = retriever.retrieve(AcceptSchema.instance(), whereClause);
        List<EnabledAccept> enabledMessages = new ArrayList<>();
        for (Map<String, String> enabledBinding : enabledBindings) {
            enabledMessages.add(new EnabledAccept(selfIdentifier, enabledBinding));
        }
        return enabledMessages;
    }
    
    public void sendEnabledAccept(
            final EnabledAccept enabledMessage,
            final String response,
            final String address,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> viableBindings = new HashMap<>();
        viableBindings.put(PurchaseParameterName._response.canonical(), response);
        viableBindings.put(PurchaseParameterName._address.canonical(), address);
        emit(enabledMessage.with(viableBindings, receiver));
    }

    public List<EnabledReject> retrieveEnabledRejects(
            final String whereClause) {
        List<Map<String, String>> enabledBindings = retriever.retrieve(RejectSchema.instance(), whereClause);
        List<EnabledReject> enabledMessages = new ArrayList<>();
        for (Map<String, String> enabledBinding : enabledBindings) {
            enabledMessages.add(new EnabledReject(selfIdentifier, enabledBinding));
        }
        return enabledMessages;
    }
    
    public void sendEnabledReject(
            final EnabledReject enabledMessage,
            final String response,
            final String done,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> viableBindings = new HashMap<>();
        viableBindings.put(PurchaseParameterName._response.canonical(), response);
        viableBindings.put(PurchaseParameterName._done.canonical(), done);
        emit(enabledMessage.with(viableBindings, receiver));
    }
    
    protected abstract void handleQuote(Quote receivedMessage);
    
    protected abstract void handleDelivery(Delivery receivedMessage);
    
}
