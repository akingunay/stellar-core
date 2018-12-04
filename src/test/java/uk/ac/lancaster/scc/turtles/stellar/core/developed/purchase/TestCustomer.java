package uk.ac.lancaster.scc.turtles.stellar.core.developed.purchase;

import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase.CustomerAgent;
import uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase.Delivery;
import uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase.EnabledRfq;
import uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase.Quote;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLServerMetadata;

public class TestCustomer extends CustomerAgent implements Runnable {

    private final AgentIdentifier agentIdentifier;
    private int actCount;
    private final int MAX_ACT = 1;
    
    public static TestCustomer create() {
        return new TestCustomer(
                new AgentIdentifier("test-customer", "127.0.0.1", "41682"),
                new MySQLHistory(new MySQLServerMetadata("127.0.0.1", "customer", "root", "cigaacopso")));
    }
    
    public TestCustomer(AgentIdentifier agentIdentifier, MySQLHistory history) {
        super(agentIdentifier, history);
        this.agentIdentifier = agentIdentifier;
        this.actCount = 0;
    }
    
    @Override
    protected void handleQuote(Quote receivedMessage) {
        System.out.println(agentIdentifier.getName() + ": received " + receivedMessage.getName());
        stop();
        
    }

    @Override
    protected void handleDelivery(Delivery receivedMessage) {
    }

    @Override
    protected void beforeStart() {
    }

    @Override
    protected void afterStart() {
        EnabledRfq rfq = retrieveEnabledRfq();
        sendEnabledRfq(rfq, "item", new AgentIdentifier("test-merchant", "127.0.0.1", "41681"));
    }

    @Override
    protected void act() {

    }

    @Override
    protected void beforeStop() {
    }

    @Override
    protected void afterStop() {
        // call clear() to close database connection etc.
    }

    @Override
    public void run() {
        execute();
    }

}
