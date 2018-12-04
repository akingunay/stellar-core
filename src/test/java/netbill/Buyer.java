package netbill;

import netbill.generated.BuyerAgent;
import netbill.generated.EnabledAcceptQuote;
import netbill.generated.EnabledRejectQuote;
import netbill.generated.EnabledRfq;
import netbill.generated.Error;
import netbill.generated.Goods;
import netbill.generated.NetBillParameterName;
import netbill.generated.Quote;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

public class Buyer extends BuyerAgent implements Runnable {

    private final AgentIdentifier selfIdentifier;
    private final AgentIdentifier sellerIdentifier;
    private int requestCount;
    private int enactmentCount;

    private final static int ENACTMENT_LIMIT = 4;

    public Buyer(AgentIdentifier selfIdentifier, AgentIdentifier sellerIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.sellerIdentifier = sellerIdentifier;
        this.requestCount = 0;
        this.enactmentCount = 0;
    }

    @Override
    protected void handleQuote(Quote receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECEIVED "
                + receivedMessage.getName() + "["
                + receivedMessage.bindingOfPid() + ", "
                + receivedMessage.bindingOfItem() + ", "
                + receivedMessage.bindingOfPrice() + "]"
                + " FROM " + receivedMessage.getSender().getName());

        enactmentCount++;

        if (enactmentCount % 2 == 0) {
            EnabledAcceptQuote enabledAcceptQuote = retrieveEnabledAcceptQuote().get(0);

            // begin logic to set parameter bindings
            String quoteResponse = "quoteResponse-" + enactmentCount;
            String ccard = "ccard-" + enactmentCount;
            // end logic to set parameter bindings

            sendEnabledAcceptQuote(enabledAcceptQuote, quoteResponse, ccard, receivedMessage.getSender());

            System.out.println(selfIdentifier.getName() + " SENT "
                    + enabledAcceptQuote.getName() + "["
                    + enabledAcceptQuote.getBinding(NetBillParameterName._pid.canonical()) + ", "
                    + enabledAcceptQuote.getBinding(NetBillParameterName._item.canonical()) + ", "
                    + enabledAcceptQuote.getBinding(NetBillParameterName._price.canonical()) + ", "
                    + quoteResponse + ", " + ccard + "]"
                    + " TO " + sellerIdentifier.getName());
        } else {
            EnabledRejectQuote enabledRejectQuote = retrieveEnabledRejectQuote().get(0);

            // begin logic to set parameter bindings
            String quoteResponse = "quoteResponse-" + enactmentCount;
            String outcome = "outcome-" + enactmentCount;
            // end logic to set parameter bindings

            sendEnabledRejectQuote(enabledRejectQuote, quoteResponse, outcome, receivedMessage.getSender());

            System.out.println(selfIdentifier.getName() + " SENT "
                    + enabledRejectQuote.getName() + "["
                    + enabledRejectQuote.getBinding(NetBillParameterName._pid.canonical()) + ", "
                    + enabledRejectQuote.getBinding(NetBillParameterName._item.canonical()) + ", "
                    + enabledRejectQuote.getBinding(NetBillParameterName._price.canonical()) + ", "
                    + quoteResponse + ", " + outcome + "]"
                    + " TO " + sellerIdentifier.getName());

            if (enactmentCount == ENACTMENT_LIMIT) {
                stop();
            }
        }

    }

    @Override
    protected void handleGoods(Goods receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECEIVED "
                + receivedMessage.getName() + "["
                + receivedMessage.bindingOfPid() + ", "
                + receivedMessage.bindingOfItem() + ", "
                + receivedMessage.bindingOfReceipt() + ", "
                + receivedMessage.bindingOfOutcome() + "]"
                + " FROM " + receivedMessage.getSender().getName());

        if (enactmentCount == ENACTMENT_LIMIT) {
            stop();
        }
    }

    @Override
    protected void handleError(Error receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECEIVED "
                + receivedMessage.getName() + "["
                + receivedMessage.bindingOfPid() + ", "
                + receivedMessage.bindingOfItem() + ", "
                + receivedMessage.bindingOfDenialReason() + ", "
                + receivedMessage.bindingOfOutcome() + "]"
                + " FROM " + receivedMessage.getSender().getName());

        if (enactmentCount == ENACTMENT_LIMIT) {
            stop();
        }
    }

    @Override
    protected void beforeStart() {
        System.out.println("Buyer is ready to start.");
    }

    @Override
    protected void afterStart() {
        System.out.println("Buyer has started.");
    }

    @Override
    protected void act() {
        if (requestCount < ENACTMENT_LIMIT) {
            EnabledRfq enabledRfq = retrieveEnabledRfq();

            // begin logic to set parameter bindings
            requestCount++;
            String item = "item-" + requestCount;
            // end logic to set parameter bindings

            sendEnabledRfq(enabledRfq, item, sellerIdentifier);

            System.out.println(selfIdentifier.getName() + " SENT "
                    + enabledRfq.getName() + "[" + item + "]"
                    + " TO " + sellerIdentifier.getName());
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    @Override
    protected void beforeStop() {
        System.out.println("Buyer will stop.");
    }

    @Override
    protected void afterStop() {
        System.out.println("Buyer stopped.");
    }

    @Override
    public void run() {
        execute();
    }
}
