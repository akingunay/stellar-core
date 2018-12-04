package netbill;

import netbill.generated.AcceptQuote;
import netbill.generated.Charge;
import netbill.generated.Deny;
import netbill.generated.EnabledAcceptQuote;
import netbill.generated.EnabledCharge;
import netbill.generated.EnabledError;
import netbill.generated.EnabledGoods;
import netbill.generated.EnabledQuote;
import netbill.generated.NetBillParameterName;
import netbill.generated.RejectQuote;
import netbill.generated.Rfq;
import netbill.generated.SellerAgent;
import netbill.generated.Transfer;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

public class Seller extends SellerAgent implements Runnable {

    private AgentIdentifier selfIdentifier;
    private AgentIdentifier buyerIdentifier;
    private AgentIdentifier bankIdentifier;
    private int outcomeCount;

    private final static int ENACTMENT_LIMIT = 4;

    public Seller(AgentIdentifier selfIdentifier, AgentIdentifier buyerIdentifier, AgentIdentifier bankIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.buyerIdentifier = buyerIdentifier;
        this.bankIdentifier = bankIdentifier;
        outcomeCount = 0;
    }

    @Override
    protected void handleRfq(Rfq receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECEIVED "
                + receivedMessage.getName() + "["
                + receivedMessage.bindingOfPid() + ", "
                + receivedMessage.bindingOfItem() + "]"
                + " FROM " + receivedMessage.getSender().getName());

        EnabledQuote enabledQuote = retrieveEnabledQuote().get(0);

        // begin logic to set parameter bindings
        outcomeCount++;
        String price = "price-" + outcomeCount;
        // end logic to set parameter bindings

        sendEnabledQuote(enabledQuote, price, receivedMessage.getSender());

        System.out.println(selfIdentifier.getName() + " SENT "
                + enabledQuote.getName() + "["
                + enabledQuote.getBinding(NetBillParameterName._pid.canonical()) + ", "
                + enabledQuote.getBinding(NetBillParameterName._item.canonical()) + ", "
                + price + "]"
                + " TO " + receivedMessage.getSender().getName());
    }

    @Override
    protected void handleAcceptQuote(AcceptQuote receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECEIVED "
                + receivedMessage.getName() + "["
                + receivedMessage.bindingOfPid() + ", "
                + receivedMessage.bindingOfItem() + ", "
                + receivedMessage.bindingOfPrice() + ", "
                + receivedMessage.bindingOfQuoteResponse() + ", "
                + receivedMessage.bindingOfCcard() + "]"
                + " FROM " + receivedMessage.getSender().getName());

        EnabledCharge enabledCharge = retrieveEnabledCharge().get(0);

        // begin logic to set parameter bindings
        String account = "account-" + outcomeCount;
        // end logic to set parameter bindings

        sendEnabledCharge(enabledCharge, account, bankIdentifier);

        System.out.println(selfIdentifier.getName() + " SENT "
                + enabledCharge.getName() + "["
                + enabledCharge.getBinding(NetBillParameterName._pid.canonical()) + ", "
                + enabledCharge.getBinding(NetBillParameterName._price.canonical()) + ", "
                + enabledCharge.getBinding(NetBillParameterName._ccard.canonical()) + ", "
                + account + "]"
                + " TO " + bankIdentifier.getName());
    }

    @Override
    protected void handleRejectQuote(RejectQuote receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECEIVED "
                + receivedMessage.getName() + "["
                + receivedMessage.bindingOfPid() + ", "
                + receivedMessage.bindingOfItem() + ", "
                + receivedMessage.bindingOfQuoteResponse() + ", "
                + receivedMessage.bindingOfOutcome() + "]"
                + " FROM " + receivedMessage.getSender().getName());

        if (outcomeCount == ENACTMENT_LIMIT) {
            stop();
        }

    }

    @Override
    protected void handleTransfer(Transfer receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECEIVED "
                + receivedMessage.getName() + "["
                + receivedMessage.bindingOfPid() + ", "
                + receivedMessage.bindingOfPrice() + ", "
                + receivedMessage.bindingOfCcard() + ", "
                + receivedMessage.bindingOfAccount() + ", "
                + receivedMessage.bindingOfChargeResponse() + ", "
                + receivedMessage.bindingOfReceipt() + "]"
                + " FROM " + receivedMessage.getSender().getName());

        EnabledGoods enabledGoods = retrieveEnabledGoods().get(0);

        // begin logic to set parameter bindings
        String outcome = "outcome-" + outcomeCount;
        // end logic to set parameter bindings

        sendEnabledGoods(enabledGoods, outcome, buyerIdentifier);

        System.out.println(selfIdentifier.getName() + " SENT "
                + enabledGoods.getName() + "["
                + enabledGoods.getBinding(NetBillParameterName._pid.canonical()) + ", "
                + enabledGoods.getBinding(NetBillParameterName._item.canonical()) + ", "
                + enabledGoods.getBinding(NetBillParameterName._receipt.canonical()) + ", "
                + outcome + "]"
                + " TO " + buyerIdentifier.getName());
        if (outcomeCount == ENACTMENT_LIMIT) {
            stop();
        }
    }

    @Override
    protected void handleDeny(Deny receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECEIVED "
                + receivedMessage.getName() + "["
                + receivedMessage.bindingOfPid() + ", "
                + receivedMessage.bindingOfPrice() + ", "
                + receivedMessage.bindingOfCcard() + ", "
                + receivedMessage.bindingOfAccount() + ", "
                + receivedMessage.bindingOfChargeResponse() + ", "
                + receivedMessage.bindingOfDenialReason() + "]"
                + " FROM " + receivedMessage.getSender().getName());

        EnabledError enabledError = retrieveEnabledError().get(0);

        // begin logic to set parameter bindings
        String outcome = "outcome-" + outcomeCount;
        // end logic to set parameter bindings

        sendEnabledError(enabledError, outcome, buyerIdentifier);

        System.out.println(selfIdentifier.getName() + " SENT "
                + enabledError.getName() + "["
                + enabledError.getBinding(NetBillParameterName._pid.canonical()) + ", "
                + enabledError.getBinding(NetBillParameterName._item.canonical()) + ", "
                + enabledError.getBinding(NetBillParameterName._denialReason.canonical()) + ", "
                + outcome + "]"
                + " TO " + buyerIdentifier.getName());
        if (outcomeCount == ENACTMENT_LIMIT) {
            stop();
        }
    }

    @Override
    protected void beforeStart() {
        System.out.println("Seller is ready to start.");
    }

    @Override
    protected void afterStart() {
        System.out.println("Seller has started.");
    }

    @Override
    protected void act() {
    }

    @Override
    protected void beforeStop() {
        System.out.println("Seller will stop.");
    }

    @Override
    protected void afterStop() {
        System.out.println("Seller stopped.");
    }

    @Override
    public void run() {
        execute();
    }

}
