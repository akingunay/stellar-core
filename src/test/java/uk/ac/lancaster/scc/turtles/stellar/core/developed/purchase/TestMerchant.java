package uk.ac.lancaster.scc.turtles.stellar.core.developed.purchase;

import java.util.List;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase.Accept;
import uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase.EnabledQuote;
import uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase.MerchantAgent;
import uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase.Reject;
import uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase.Rfq;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLServerMetadata;

public class TestMerchant extends MerchantAgent implements Runnable {

    private final AgentIdentifier agentIdentifier;
    private int actCount;
    private final int MAX_ACT = 1;
    
    public static TestMerchant create() {
        return new TestMerchant(
                new AgentIdentifier("test-merchant", "127.0.0.1", "41681"),
                new MySQLHistory(new MySQLServerMetadata("127.0.0.1", "merchant", "root", "cigaacopso")));
    }
            
    private TestMerchant(AgentIdentifier agentIdentifier, MySQLHistory history) {
        super(agentIdentifier, history);
        this.agentIdentifier = agentIdentifier;
        this.actCount = 0;
    }
    
    @Override
    protected void handleRfq(Rfq receivedMessage) {
        System.out.println(agentIdentifier.getName() + ": received " + receivedMessage.getName());
        List<EnabledQuote> eqs = retrieveEnabledQuote(" WHERE id = \"" + receivedMessage.getBinding("id") + "\"");
        sendEnabledQuote(eqs.get(0), "1", new AgentIdentifier("test-customer", "127.0.0.1", "41682"));
        stop();
    }

    @Override
    protected void handleAccept(Accept receivedMessage) {
    }

    @Override
    protected void handleReject(Reject receivedMessage) {
    }

    @Override
    protected void beforeStart() {
        System.out.println(agentIdentifier.getName() + ": handling before start");
    }

    @Override
    protected void afterStart() {
        System.out.println(agentIdentifier.getName() + ": handling after start");
    }

    @Override
    protected void act() {
    }

    @Override
    protected void beforeStop() {
        System.out.println(agentIdentifier.getName() + ": handling before stop");
    }

    @Override
    protected void afterStop() {
        System.out.println(agentIdentifier.getName() + ": handling after stop");
    }

    @Override
    public void run() {
        execute();
    }

}
