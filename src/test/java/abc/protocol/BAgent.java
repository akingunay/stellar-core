package abc.protocol;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BAgent extends uk.ac.lancaster.scc.turtles.stellar.core.agent.BasicAgent {
    
    private final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier;
    private final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever retriever;
    private final uk.ac.lancaster.scc.turtles.stellar.core.util.IdentifierGenerator generator;

    protected BAgent(
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier, 
            final uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory history) {
        super(buildEndpoint(selfIdentifier, history));
        this.selfIdentifier = selfIdentifier;
        this.retriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(
                new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(
                        history, ABCProtocolSchema.instance(),
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
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever enactmentRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(history, ABCProtocolSchema.instance(), compiler);
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever enabledRetriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(enactmentRetriever);
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker viabilityChecker = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLViabilityChecker(enactmentRetriever);
        return new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEndpoint(sender, receiver, ABCProtocolSchema.instance(), history, compiler, enabledRetriever, viabilityChecker);
    }
    
    @Override
    protected final void handleMessage(
            final uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message message) {
        uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage bsplMessage = (uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLMessage) message;
        if (bsplMessage.getName().equals(MsgASchema.instance().getName())) {
            handleMsgA(new MsgA(bsplMessage));
            return;
        }
    }

    public List<EnabledMsgB> retrieveEnabledMsgB () {
        return retrieveEnabledMsgB("");
    }
    
    public List<EnabledMsgB> retrieveEnabledMsgB (
            final String whereClause) {
        List<Map<String, String>> enabledBindings = retriever.retrieve(MsgBSchema.instance(), whereClause);
        List<EnabledMsgB> enabledMessages = new ArrayList<>();
        for (Map<String, String> enabledBinding : enabledBindings) {
            enabledMessages.add(new EnabledMsgB(selfIdentifier, enabledBinding));
        }
        return enabledMessages;
    }

    public void sendEnabledMsgB(
            final EnabledMsgB enabledMessage,
            final String p2,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> viableBindings = new HashMap<>();
        viableBindings.put(ABCParameterName._p2.canonical(), p2);
        emit(enabledMessage.with(viableBindings, receiver));
    }
    
    protected abstract void handleMsgA(MsgA receivedMessage);
    
}