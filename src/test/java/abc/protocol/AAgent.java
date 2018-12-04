package abc.protocol;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import uk.ac.lancaster.scc.turtles.stellar.core.history.Relation;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever;

public abstract class AAgent  extends uk.ac.lancaster.scc.turtles.stellar.core.agent.BasicAgent {
    
    private final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier;
    private final uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever retriever;
    private final uk.ac.lancaster.scc.turtles.stellar.core.util.IdentifierGenerator generator;
    private final MySQLHistory history;

    protected AAgent(
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier selfIdentifier, 
            final uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory history) {
        super(buildEndpoint(selfIdentifier, history));
        this.selfIdentifier = selfIdentifier;
        this.retriever = new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnabledMessageRetriever(
                new uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLEnactmentRetriever(
                        history, ABCProtocolSchema.instance(),
                        new uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler()));
        this.generator = new uk.ac.lancaster.scc.turtles.stellar.core.util.IdentifierGenerator();
        this.history = history;
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
        if (bsplMessage.getName().equals(MsgCSchema.instance().getName())) {
            handleMsgC(new MsgC(bsplMessage));
            return;
        }
    }

    public EnabledMsgA retrieveEnabledMsgA() {
        return new EnabledMsgA(selfIdentifier, new HashMap<>());
    }

    public void sendEnabledMsgA(
            final EnabledMsgA enabledMessage,
            final String p1,
            final uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier receiver) {
        Map<String, String> viableBindings = new HashMap<>();
        viableBindings.put(ABCParameterName._id.canonical(), generator.unique());
        viableBindings.put(ABCParameterName._p1.canonical(), p1);
        emit(enabledMessage.with(viableBindings, receiver));
    }
    
    protected abstract void handleMsgC(MsgC receivedMessage);
    
    public void x(String rID) {
        BSPLEnactmentRetriever eRet = new BSPLEnactmentRetriever(history, ABCProtocolSchema.instance(), new MySQLStatementCompiler());
        eRet.retrieveEnactments(" WHERE rID = \"" + rID + "\" AND p1 IS NOT NULL AND p2 IS NOT NULL;");
        Relation r = history.query("SLELECT rID");
    }
}