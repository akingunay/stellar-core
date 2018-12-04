package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.RelationalHistory;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.SQLStatementCompiler;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Endpoint;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;
import uk.ac.lancaster.scc.turtles.stellar.core.communication.MessageReceiver;
import uk.ac.lancaster.scc.turtles.stellar.core.communication.MessageSender;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.exception.InAdornmentViolationException;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.exception.NilAdornmentViolationException;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.exception.OutAdornmentViolationException;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.exception.UnboundParameterException;

public class BSPLEndpoint implements Endpoint {

    private final MessageSender sender;
    private final MessageReceiver receiver;
    private final BSPLProtocolSchema protocolSchema;
    private final RelationalHistory history;
    private final SQLStatementCompiler compiler;
    private final BSPLEnabledMessageRetriever messageRetriever;
    private final BSPLViabilityChecker viabilityChecker;
    
    public BSPLEndpoint(MessageSender sender, MessageReceiver receiver, 
            BSPLProtocolSchema protocolSchema, RelationalHistory history, SQLStatementCompiler compiler,
            BSPLEnabledMessageRetriever messageRetriever, BSPLViabilityChecker viabilityChecker) {
        this.sender = sender;
        this.receiver = receiver;
        this.protocolSchema = protocolSchema;
        this.history = history;
        this.compiler = compiler;
        this.messageRetriever = messageRetriever;
        this.viabilityChecker = viabilityChecker;
    }
    
    @Override
    public void start() {
        receiver.start();
    }

    @Override
    public void stop() {
        receiver.stop();
    }

    @Override
    public Message pollReceivedMessage() {
        String wireMessage = receiver.pollWireMessage();
        if (wireMessage == null) {
            return null;
        } 
        BSPLMessage message = new BSPLMessage(wireMessage);
        BSPLReferenceSchema referenceSchema = protocolSchema.getReferenceSchema(message.getName());
        if (referenceSchema == null) {
            // TODO log warning
        }
        //if (!viabilityChecker.check(message, referenceSchema).equals(ViabilityCheckResult.VIABLE)) {
            // TODO log warning
        //}
        addMessageToHistory(message);
        return message;
    }

    @Override
    public Message takeReceivedMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void send(Message message) {
        BSPLMessage bsplMessage = (BSPLMessage) message;
        //checkViability(bsplMessage);
        addMessageToHistory(bsplMessage);
        try {
            sender.send(bsplMessage.toJSON(), compileReceiversSocketAddress(bsplMessage.getReceiver()));
        } catch (IOException e){
            System.err.println("An error has occurred");
            // TODO write a clear error message
            // TODO remove the message from history
        }
    }
    
    private void checkViability(BSPLMessage message) {
        switch (viabilityChecker.check(message, protocolSchema.getReferenceSchema(message.getName()))) {
            case UNBOUND_PARAMETER:
                throw new UnboundParameterException();
            case IN_VIOLATION:
                throw new InAdornmentViolationException();
            case OUT_VIOLATION:
                throw new OutAdornmentViolationException();
            case NIL_VIOLATION:
                throw new NilAdornmentViolationException();
        }
    }
    
    private void addMessageToHistory(BSPLMessage message) {
        BSPLReferenceSchema referecenSchema = protocolSchema.getReferenceSchema(message.getName());
        List<String> parameters = referecenSchema.getParameters();
        List<String> bindings = new ArrayList<>();
        for (String parameter : parameters) {
            bindings.add(message.getBinding(parameter));
        }
        history.update(compiler.compileInsertStatement(message.getName(), parameters, bindings));
    }
    
    private InetSocketAddress compileReceiversSocketAddress(AgentIdentifier agentIdentifier) {
        return new InetSocketAddress(agentIdentifier.getHost(), Integer.parseInt(agentIdentifier.getPort()));
    }
}
